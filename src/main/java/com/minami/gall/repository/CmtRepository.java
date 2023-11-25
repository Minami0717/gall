package com.minami.gall.repository;

import com.minami.gall.entity.Cmt;
import com.minami.gall.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmtRepository extends JpaRepository<Cmt, Long> {
    List<Cmt> findByPost(Post post);
}
