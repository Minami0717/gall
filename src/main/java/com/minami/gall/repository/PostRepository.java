package com.minami.gall.repository;

import com.minami.gall.entity.Gall;
import com.minami.gall.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByGallOrderByPostIdDesc(Gall gall, Pageable pageable);
}
