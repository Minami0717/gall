package com.minami.gall.repository;

import com.minami.gall.entity.Gall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GallRepository extends JpaRepository<Gall, Long> {
}
