package com.minami.gall.cmt.model;

import lombok.Data;

@Data
public class CmtInsDto {
    private Long postId;
    private String content;
    private String writer;
    private String pw;
    private String ip;
}
