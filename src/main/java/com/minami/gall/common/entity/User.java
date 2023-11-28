package com.minami.gall.common.entity;

import com.minami.gall.common.enums.TrueFalse;
import com.minami.gall.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
public class User extends BaseEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long userId;

    @Column(nullable = false, length = 50, unique = true)
    private String uid;

    @Column(nullable = false, length = 100)
    private String upw;

    @Column(nullable = false, length = 50)
    private String nick;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, columnDefinition = "tinyint")
    private int fixedYn;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private TrueFalse deleted;
}
