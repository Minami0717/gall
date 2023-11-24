package com.minami.gall.post.model;

import com.minami.gall.entity.Cmt;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostDetailVo {
    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String ip;
    private LocalDateTime createdAt;
    private int hits;
    private int recoNum;
    private int decoNum;
    private List<String> imgs;
    private List<Cmt> cmts;
}
