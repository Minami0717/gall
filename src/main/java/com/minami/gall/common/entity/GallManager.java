package com.minami.gall.common.entity;

import com.minami.gall.common.enums.TrueFalse;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
public class GallManager {
    @EmbeddedId
    private GallManagerID gallManagerID;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private TrueFalse subYn;
}
