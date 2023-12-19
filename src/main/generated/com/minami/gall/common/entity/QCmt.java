package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCmt is a Querydsl query type for Cmt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCmt extends EntityPathBase<Cmt> {

    private static final long serialVersionUID = -1405343004L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCmt cmt = new QCmt("cmt");

    public final NumberPath<Long> cmtId = createNumber("cmtId", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath ip = createString("ip");

    public final QPost post;

    public final StringPath pw = createString("pw");

    public final QUser user;

    public final StringPath writer = createString("writer");

    public QCmt(String variable) {
        this(Cmt.class, forVariable(variable), INITS);
    }

    public QCmt(Path<? extends Cmt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCmt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCmt(PathMetadata metadata, PathInits inits) {
        this(Cmt.class, metadata, inits);
    }

    public QCmt(Class<? extends Cmt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

