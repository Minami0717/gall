package com.minami.gall.post.model;

import lombok.Data;

import java.util.List;

@Data
public class PostInsParam {
    private String gallId;
    private String title;
    private String content;
    private String writer;
    private String pw;
    private String ip;
    private List<String> imgUrls;
}
