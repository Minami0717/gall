package com.minami.gall.entity;

import com.minami.gall.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "gall_id", nullable = false)
    private Gall gall;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false, length = 50)
    private String writer;

    @Column(nullable = false, columnDefinition = "char(7)")
    private String ip;

    @Column(nullable = false, columnDefinition = "int unsigned")
    @ColumnDefault("0")
    private int hits;

    @Column(nullable = false, columnDefinition = "int unsigned")
    @ColumnDefault("0")
    private int recoNum;

    @Column(nullable = false, columnDefinition = "int unsigned")
    @ColumnDefault("0")
    private int decoNum;

    @Column(nullable = false, length = 100)
    private String pw;

    @OneToMany(mappedBy = "post")
    private List<Cmt> cmts;

    @OneToMany(mappedBy = "postImgID.post")
    private List<PostImg> imgs;
}
