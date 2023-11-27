package com.minami.gall.post;

import com.minami.gall.entity.Gall;
import com.minami.gall.entity.Post;
import com.minami.gall.entity.PostImg;
import com.minami.gall.entity.PostImgID;
import com.minami.gall.post.model.*;
import com.minami.gall.repository.PostImgRepository;
import com.minami.gall.repository.PostRepository;
import com.minami.gall.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository rep;
    private final PostImgRepository imgRep;

    @Value("${file-dir}")
    private String fileDir;

    @Value("${img-url}")
    private String imgUrl;

    public PageVo getPostsByGallId(Long id, Pageable pageable) {
        Page<Post> posts = rep.findByGallOrderByPostIdDesc(Gall.builder().gallId(id).build(), pageable);
        return PageVo.builder()
                .posts(posts.map(p -> PostVo.builder()
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

    public void writePost(List<MultipartFile> imgList, PostInsDto dto) {
        Post post = rep.save(Post.builder()
                .gall(Gall.builder().gallId(dto.getGallId()).build())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .ip(dto.getIp())
                .pw(dto.getPw())
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
    public PostDetailVo getPostDetail(Long id) {
        Post p = getPostById(id);
        p.upHits();

        return PostDetailVo.builder()
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
                        "%s/post/%d/%s", imgUrl, p.getPostId(), i.getPostImgID().getImg())).toList())
                .cmts(p.getCmts())
                .build();
    }

    public boolean pwCheck(PostPwCheckDto dto) {
        Post p = getPostById(dto.getPostId());
        return p.getPw().equals(dto.getPw());
    }

    public PostSimpleVo getPostSimple(Long id) {
        Post p = getPostById(id);
        return PostSimpleVo.builder()
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
    public void updPost(PostUpdDto dto) {
        getPostById(dto.getPostId()).updPost(dto.getTitle(), dto.getContent());
    }
}
