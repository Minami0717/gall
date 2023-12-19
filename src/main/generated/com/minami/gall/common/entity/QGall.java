package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGall is a Querydsl query type for Gall
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGall extends EntityPathBase<Gall> {

    private static final long serialVersionUID = -615852672L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGall gall = new QGall("gall");

    public final com.minami.gall.common.jpa.QBaseEntity _super = new com.minami.gall.common.jpa.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QGallCategory gallCategory;

    public final StringPath gallId = createString("gallId");

    public final ListPath<GallManager, QGallManager> gallManagers = this.<GallManager, QGallManager>createList("gallManagers", GallManager.class, QGallManager.class, PathInits.DIRECT2);

    public final StringPath img = createString("img");

    public final StringPath intro = createString("intro");

    public final StringPath nm = createString("nm");

    public final NumberPath<Integer> standardNum = createNumber("standardNum", Integer.class);

    public final EnumPath<com.minami.gall.common.enums.GallType> type = createEnum("type", com.minami.gall.common.enums.GallType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QGall(String variable) {
        this(Gall.class, forVariable(variable), INITS);
    }

    public QGall(Path<? extends Gall> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGall(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGall(PathMetadata metadata, PathInits inits) {
        this(Gall.class, metadata, inits);
    }

    public QGall(Class<? extends Gall> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gallCategory = inits.isInitialized("gallCategory") ? new QGallCategory(forProperty("gallCategory")) : null;
    }

}

