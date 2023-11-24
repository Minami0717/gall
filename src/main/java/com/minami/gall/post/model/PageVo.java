package com.minami.gall.post.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageVo {
    private List<PostVo> posts;
    private int totalPage;
}
