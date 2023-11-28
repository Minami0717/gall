package com.minami.gall.cmt.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CmtDto {
    private Long cmtId;
    private String content;
    private String writer;
    private String ip;
    private String createdAt;
}
