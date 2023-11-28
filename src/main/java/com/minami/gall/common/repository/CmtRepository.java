package com.minami.gall.common.repository;

import com.minami.gall.common.entity.Cmt;
import com.minami.gall.common.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmtRepository extends JpaRepository<Cmt, Long> {
    List<Cmt> findByPost(Post post);
}
