package com.minami.gall.post.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostSimpleVo {
    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String pw;
}
