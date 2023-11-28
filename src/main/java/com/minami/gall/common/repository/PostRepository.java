package com.minami.gall.common.repository;

import com.minami.gall.common.entity.Gall;
import com.minami.gall.common.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByGallOrderByPostIdDesc(Gall gall, Pageable pageable);
    Page<Post> findByGallAndRecoNumOrderByPostIdDesc(Gall gall, int recoNum, Pageable pageable);
}
