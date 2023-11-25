package com.minami.gall.cmt.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CmtVo {
    private Long cmtId;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
