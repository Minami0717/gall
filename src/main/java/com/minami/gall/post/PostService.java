package com.minami.gall.post;

import com.minami.gall.entity.Gall;
import com.minami.gall.entity.Post;
import com.minami.gall.entity.PostImg;
import com.minami.gall.entity.PostImgID;
import com.minami.gall.post.model.PageVo;
import com.minami.gall.post.model.PostDetailVo;
import com.minami.gall.post.model.PostInsDto;
import com.minami.gall.post.model.PostVo;
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
    public PostDetailVo getPostById(Long id) {
        Post p = rep.findById(id).orElseThrow();
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
}
