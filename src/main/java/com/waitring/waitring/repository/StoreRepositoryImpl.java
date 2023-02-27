package com.waitring.waitring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.waitring.waitring.entity.Store;
import lombok.RequiredArgsConstructor;

import static com.waitring.waitring.entity.QMenu.menu;
import static com.waitring.waitring.entity.QStore.store;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Store getStoreById(Long storeId) {
        return queryFactory
                .selectFrom(store)
                .join(store.menus, menu).fetchJoin()
                .where(store.id.eq(storeId))
                .fetchOne();
    }
}
