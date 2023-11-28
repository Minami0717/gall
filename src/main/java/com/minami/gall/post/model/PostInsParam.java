package com.minami.gall.post.model;

import lombok.Data;

@Data
public class PostInsParam {
    private Long gallId;
    private String title;
    private String content;
    private String writer;
    private String pw;
    private String ip;
}