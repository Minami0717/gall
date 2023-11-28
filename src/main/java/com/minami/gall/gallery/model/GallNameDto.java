package com.minami.gall.gallery.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GallNameDto {
    private Long gallId;
    private String nm;
}
