package com.minami.gall.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
public class GallManagerID implements Serializable {
    @ManyToOne
    @JoinColumn(name = "gall_id")
    private Gall gall;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
