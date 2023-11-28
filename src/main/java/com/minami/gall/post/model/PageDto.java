package com.minami.gall.post.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageDto {
    private List<PostDto> posts;
    private int totalPage;
}
