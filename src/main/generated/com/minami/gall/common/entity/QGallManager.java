package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGallManager is a Querydsl query type for GallManager
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGallManager extends EntityPathBase<GallManager> {

    private static final long serialVersionUID = -1654414835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGallManager gallManager = new QGallManager("gallManager");

    public final QGallManagerID gallManagerID;

    public final EnumPath<com.minami.gall.common.enums.TrueFalse> subYn = createEnum("subYn", com.minami.gall.common.enums.TrueFalse.class);

    public QGallManager(String variable) {
        this(GallManager.class, forVariable(variable), INITS);
    }

    public QGallManager(Path<? extends GallManager> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGallManager(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGallManager(PathMetadata metadata, PathInits inits) {
        this(GallManager.class, metadata, inits);
    }

    public QGallManager(Class<? extends GallManager> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gallManagerID = inits.isInitialized("gallManagerID") ? new QGallManagerID(forProperty("gallManagerID"), inits.get("gallManagerID")) : null;
    }

}

