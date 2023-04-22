package com.waitring.waitring.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.waitring.waitring.entity.*
import com.waitring.waitring.entity.QMenu.menu
import com.waitring.waitring.entity.QStore.store
import com.waitring.waitring.entity.QStoreKeyword.storeKeyword

class StoreRepositoryImpl(private val queryFactory: JPAQueryFactory) : StoreRepositoryCustom {
    override fun getMenusByStore(store: Store): List<Menu> {
        return queryFactory
                .selectFrom(menu)
                .where(menu.store.eq(store))
                .fetch()
    }

    override fun getKeywordsByStore(store: Store): List<Keyword> {
        return queryFactory
                .select(storeKeyword.keyword)
                .from(storeKeyword)
                .where(storeKeyword.store.`in`(store))
                .fetch()
    }

    override fun getStoreListByWord(word: String): List<Store> {
        return queryFactory
                .selectFrom(store)
                .leftJoin(store._storeKeywords, storeKeyword).fetchJoin()
                .leftJoin(storeKeyword.keyword).fetchJoin()
                .where(store.name.contains(word)
                        .or(store.areaDong.contains(word))
                        .or(storeKeyword.keyword.name.contains(word)))
                .distinct()
                .fetch()
    }
}
