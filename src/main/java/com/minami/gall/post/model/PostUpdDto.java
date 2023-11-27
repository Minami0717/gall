package com.minami.gall.post.model;

import lombok.Data;

@Data
public class PostUpdDto {
    private Long postId;
    private String title;
    private String content;
}
