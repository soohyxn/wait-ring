package com.waitring.waitring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.StoreKeyword;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.dsl.Expressions.list;
import static com.waitring.waitring.entity.QKeyword.keyword;
import static com.waitring.waitring.entity.QStoreKeyword.storeKeyword;

@RequiredArgsConstructor
public class StoreKeywordRepositoryImpl implements StoreKeywordRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StoreKeyword> getKeywordByStores(List<Store> stores) {
        return queryFactory
                .select(storeKeyword)
                .from(storeKeyword)
                .join(storeKeyword.keyword, keyword).fetchJoin()
                .where(storeKeyword.store.in(stores))
                .fetch();
    }
}
