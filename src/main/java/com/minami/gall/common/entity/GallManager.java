package com.minami.gall.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class GallManager {
    @EmbeddedId
    private GallManagerID gallManagerID;

    @Column(nullable = false, columnDefinition = "tinyint")
    private int subYn;
}
