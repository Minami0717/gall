package com.minami.gall.common.repository;

import com.minami.gall.post.model.PostDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostDto> getPostListByGallId();
}
