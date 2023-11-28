package com.minami.gall.post;

import com.minami.gall.common.entity.Gall;
import com.minami.gall.common.entity.Post;
import com.minami.gall.common.entity.PostImg;
import com.minami.gall.common.entity.PostImgID;
import com.minami.gall.common.enums.Deleted;
import com.minami.gall.common.redis.RedisService;
import com.minami.gall.common.utils.TimeUtils;
import com.minami.gall.post.model.*;
import com.minami.gall.common.repository.PostImgRepository;
import com.minami.gall.common.repository.PostRepository;
import com.minami.gall.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository rep;
    private final PostImgRepository imgRep;
    private final RedisService redis;

    @Value("${file-dir}")
    private String fileDir;

    public PageDto getPostsByGallId(Long id, Pageable pageable) {
        Page<Post> posts = rep.findByGallOrderByPostIdDesc(Gall.builder().gallId(id).build(), pageable);
        return PageDto.builder()
                .posts(posts.map(p -> PostDto.builder()
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .writer(p.getWriter())
                        .ip(p.getIp())
                        .createdAt(p.getCreatedAt())
                        .hits(p.getHits())
                        .recoNum(p.getRecoNum())
                        .cmtNum(p.getCmts().size())
                        .build()).toList())
                .totalPage(posts.getTotalPages())
                .build();
    }

    public void writePost(List<MultipartFile> imgList, PostInsParam p) {
        Post post = rep.save(Post.builder()
                .gall(Gall.builder().gallId(p.getGallId()).build())
                .title(p.getTitle())
                .content(p.getContent())
                .writer(p.getWriter())
                .ip(p.getIp())
                .pw(p.getPw())
                .deleted(Deleted.FALSE)
                .build());

        if (!imgList.get(0).getOriginalFilename().isEmpty()) {
            File dir = new File(fileDir, String.valueOf(post.getPostId()));
            if (!dir.exists()) {
                dir.mkdirs();
            }

            List<PostImg> imgs = new ArrayList<>();
            for (MultipartFile img : imgList) {
                String savedFileName = FileUtils.makeRandomFileNm(img.getOriginalFilename());
                File imgFile = new File(dir.getPath(), savedFileName);
                try {
                    img.transferTo(imgFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    rep.delete(post);
                    dir.delete();
                    return;
                }

                imgs.add(PostImg.builder()
                        .postImgID(PostImgID.builder()
                                .post(post)
                                .img(savedFileName)
                                .build())
                        .build());
            }

            imgRep.saveAll(imgs);
        }
    }

    @Transactional
    public PostDetailDto getPostDetail(Long id) {
        Post p = getPostById(id);
        p.upHits();

        return PostDetailDto.builder()
                .postId(p.getPostId())
                .title(p.getTitle())
                .content(p.getContent())
                .writer(p.getWriter())
                .ip(p.getIp())
                .createdAt(p.getCreatedAt())
                .hits(p.getHits())
                .recoNum(p.getRecoNum())
                .decoNum(p.getDecoNum())
                .imgs(p.getImgs().stream().map(i -> String.format(
                        "%d/%s", p.getPostId(), i.getPostImgID().getImg())).toList())
                .cmts(p.getCmts())
                .build();
    }

    public boolean pwCheck(PostPwParam p) {
        Post post = getPostById(p.getPostId());
        return post.getPw().equals(p.getPw());
    }

    public PostSimpleDto getPostSimple(Long id) {
        Post p = getPostById(id);
        return PostSimpleDto.builder()
                .postId(p.getPostId())
                .content(p.getContent())
                .title(p.getTitle())
                .writer(p.getWriter())
                .pw(p.getPw())
                .build();
    }

    private Post getPostById(Long id) {
        return rep.findById(id).orElseThrow();
    }

    @Transactional
    public void updPost(PostUpdParam p) {
        getPostById(p.getPostId()).updPost(p);
    }

    public void delPost(Long postId) {
        rep.deleteById(postId);
    }

    @Transactional
    public PostRecoDto upRecoOrDeco(Long postId, String mode, String ip) {
        String key = String.format("%d:%s:%s", postId, ip, mode.toUpperCase());
        if (redis.getData(key) != null) { return PostRecoDto.builder().recoNum(-1).build(); }

        Post p = getPostById(postId);
        p.upRecoOrDeco(mode);

        redis.setDataExpire(key, mode, TimeUtils.timeDiffCalc());

        return PostRecoDto.builder()
                .recoNum(p.getRecoNum())
                .decoNum(p.getDecoNum())
                .build();
    }
}
