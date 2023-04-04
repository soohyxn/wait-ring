package com.waitring.waitring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.waitring.waitring.entity.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @Override
    public List<Store> getStoreListByWord(String word) {
        return queryFactory
                .selectFrom(store)
                .leftJoin(store.storeKeywords, storeKeyword).fetchJoin()
                .leftJoin(storeKeyword.keyword).fetchJoin()
                .where(store.name.contains(word)
                        .or(store.areaDong.contains(word))
                        .or(storeKeyword.keyword.name.contains(word)))
                .distinct()
                .fetch();

    }
}
