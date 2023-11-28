package com.minami.gall.common.repository;

import com.minami.gall.common.entity.PostImg;
import com.minami.gall.common.entity.PostImgID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImgRepository extends JpaRepository<PostImg, PostImgID> {
}
