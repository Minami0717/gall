package com.minami.gall.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GallCategory {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long categoryId;

    @Column(nullable = false, length = 20, unique = true)
    private String nm;
}
