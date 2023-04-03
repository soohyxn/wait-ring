package com.waitring.waitring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.waitring.waitring.entity.QMenu.menu;
import static com.waitring.waitring.entity.QStore.store;
import static com.waitring.waitring.entity.QStoreKeyword.storeKeyword;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> getMenusByStore(Store store) {
        return queryFactory
                .selectFrom(menu)
                .where(menu.store.eq(store))
                .fetch();
    }

    @Override
    public List<Keyword> getKeywordsByStore(Store store){
        return queryFactory
                .select(storeKeyword.keyword)
                .from(storeKeyword)
                .where(storeKeyword.store.in(store))
                .fetch();
    }
}
