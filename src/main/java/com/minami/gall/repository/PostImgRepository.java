package com.minami.gall.repository;

import com.minami.gall.entity.PostImg;
import com.minami.gall.entity.PostImgID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImgRepository extends JpaRepository<PostImg, PostImgID> {
}
