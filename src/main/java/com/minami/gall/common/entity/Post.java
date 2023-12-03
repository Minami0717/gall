package com.minami.gall.common.entity;

import com.minami.gall.common.enums.TrueFalse;
import com.minami.gall.common.jpa.BaseEntity;
import com.minami.gall.post.model.PostUpdParam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE post SET deleted = 1 WHERE post_id = ?")
public class Post extends BaseEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gall_id", nullable = false)
    private Gall gall;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(nullable = false)
    @ColumnDefault("0")
    private TrueFalse deleted;

    @Column(nullable = false)
    @ColumnDefault("0")
    private TrueFalse noticeYn;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Cmt> cmts;

    public void upHits() {
        hits++;
    }
    public void upRecoOrDeco(String mode) {
        switch (mode) {
            case "reco" -> recoNum++;
            case "deco" -> decoNum++;
        }
    }
    public void updPost(PostUpdParam p) {
        this.title = p.getTitle();
        this.content = p.getContent();
    }
}
