package com.minami.gall.post.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostVo {
    private Long postId;
    private String title;
    private String writer;
    private String ip;
    private LocalDateTime createdAt;
    private int hits;
    private int recoNum;
    private int cmtNum;
}
