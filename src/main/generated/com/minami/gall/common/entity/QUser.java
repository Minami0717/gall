package com.minami.gall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -615418511L;

    public static final QUser user = new QUser("user");

    public final com.minami.gall.common.jpa.QBaseEntity _super = new com.minami.gall.common.jpa.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<com.minami.gall.common.enums.TrueFalse> deleted = createEnum("deleted", com.minami.gall.common.enums.TrueFalse.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.minami.gall.common.enums.TrueFalse> fixedYn = createEnum("fixedYn", com.minami.gall.common.enums.TrueFalse.class);

    public final StringPath nick = createString("nick");

    public final StringPath uid = createString("uid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath upw = createString("upw");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

