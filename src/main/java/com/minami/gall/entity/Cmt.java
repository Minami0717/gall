package com.minami.gall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cmt {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long cmtId;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 50)
    private String writer;

    @Column(nullable = false, columnDefinition = "char(7)")
    private String ip;

    @Column(nullable = false, length = 100)
    private String pw;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    @ColumnDefault(value = "current_timestamp")
    private LocalDateTime createdAt;
}
