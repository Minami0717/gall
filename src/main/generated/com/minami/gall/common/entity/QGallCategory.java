package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGallCategory is a Querydsl query type for GallCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGallCategory extends EntityPathBase<GallCategory> {

    private static final long serialVersionUID = 179992222L;

    public static final QGallCategory gallCategory = new QGallCategory("gallCategory");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath nm = createString("nm");

    public QGallCategory(String variable) {
        super(GallCategory.class, forVariable(variable));
    }

    public QGallCategory(Path<? extends GallCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGallCategory(PathMetadata metadata) {
        super(GallCategory.class, metadata);
    }

}

