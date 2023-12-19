package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -615570874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.minami.gall.common.jpa.QBaseEntity _super = new com.minami.gall.common.jpa.QBaseEntity(this);

    public final ListPath<Cmt, QCmt> cmts = this.<Cmt, QCmt>createList("cmts", Cmt.class, QCmt.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> decoNum = createNumber("decoNum", Integer.class);

    public final EnumPath<com.minami.gall.common.enums.TrueFalse> deleted = createEnum("deleted", com.minami.gall.common.enums.TrueFalse.class);

    public final QGall gall;

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    public final StringPath ip = createString("ip");

    public final EnumPath<com.minami.gall.common.enums.TrueFalse> noticeYn = createEnum("noticeYn", com.minami.gall.common.enums.TrueFalse.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath pw = createString("pw");

    public final NumberPath<Integer> recoNum = createNumber("recoNum", Integer.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public final StringPath writer = createString("writer");

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gall = inits.isInitialized("gall") ? new QGall(forProperty("gall"), inits.get("gall")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

