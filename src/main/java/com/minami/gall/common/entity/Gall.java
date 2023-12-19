package com.minami.gall.common.entity;

import com.minami.gall.common.enums.GallType;
import com.minami.gall.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Gall extends BaseEntity {
    @Id //pk
    @Column(updatable = false, nullable = false, length = 20)
    private String gallId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private GallCategory gallCategory;

    @Column(nullable = false, length = 50, unique = true)
    private String nm;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GallType type;

    @Column(length = 100)
    private String img;

    @Column(length = 100)
    private String intro;

    @Column(nullable = false, columnDefinition = "tinyint unsigned")
    private int standardNum;

    @OneToMany(mappedBy = "gallManagerID.gall")
    private List<GallManager> gallManagers;
}
