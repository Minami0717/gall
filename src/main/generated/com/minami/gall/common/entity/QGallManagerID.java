package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGallManagerID is a Querydsl query type for GallManagerID
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGallManagerID extends BeanPath<GallManagerID> {

    private static final long serialVersionUID = -754754584L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGallManagerID gallManagerID = new QGallManagerID("gallManagerID");

    public final QGall gall;

    public final QUser user;

    public QGallManagerID(String variable) {
        this(GallManagerID.class, forVariable(variable), INITS);
    }

    public QGallManagerID(Path<? extends GallManagerID> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGallManagerID(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGallManagerID(PathMetadata metadata, PathInits inits) {
        this(GallManagerID.class, metadata, inits);
    }

    public QGallManagerID(Class<? extends GallManagerID> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gall = inits.isInitialized("gall") ? new QGall(forProperty("gall"), inits.get("gall")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

