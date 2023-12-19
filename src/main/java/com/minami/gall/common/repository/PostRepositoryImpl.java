package com.minami.gall.common.repository;

import com.minami.gall.post.model.PostDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory q;

    @Override
    public List<PostDto> getPostListByGallId() {
        return null;
    }
}
