package com.minami.gall.post;

import com.minami.gall.common.entity.Gall;
import com.minami.gall.common.entity.Post;
import com.minami.gall.common.enums.TrueFalse;
import com.minami.gall.common.redis.RedisService;
import com.minami.gall.common.utils.TimeUtils;
import com.minami.gall.post.model.*;
import com.minami.gall.common.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository rep;
    private final RedisService redis;

    public PageDto getPostsByGallId(String id, Pageable pageable, String mode) {
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

    public void writePost(PostInsParam p) {
        rep.save(Post.builder()
                .gall(Gall.builder().gallId(p.getGallId()).build())
                .title(p.getTitle())
                .content(p.getContent())
                .writer(p.getWriter())
                .ip(p.getIp())
                .pw(p.getPw())
                .deleted(TrueFalse.FALSE)
                .noticeYn(TrueFalse.FALSE)
                .build());
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
        try {
            if (redis.getData(key) != null) { return PostRecoDto.builder().recoNum(-1).build(); }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Post p = getPostById(postId);
        p.upRecoOrDeco(mode);

        redis.setDataExpire(key, mode, TimeUtils.timeDiffCalc());

        return PostRecoDto.builder()
                .recoNum(p.getRecoNum())
                .decoNum(p.getDecoNum())
                .build();
    }
}
