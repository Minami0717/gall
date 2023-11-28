package com.minami.gall.gallery.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class GallInfoDto {
    private Long gallId;
    private String nm;
    private String img;
    private String intro;
    private String manager;
    private List<String> subManagers;
    private LocalDate createdAt;
}
