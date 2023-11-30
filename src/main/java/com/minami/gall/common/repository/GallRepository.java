package com.minami.gall.common.repository;

import com.minami.gall.common.entity.Gall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GallRepository extends JpaRepository<Gall, String> {
}
