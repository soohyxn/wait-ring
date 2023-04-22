package com.waitring.waitring.repository

import com.waitring.waitring.entity.Keyword
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.entity.Store

interface StoreRepositoryCustom {
    fun getMenusByStore(store: Store): List<Menu>
    fun getKeywordsByStore(store: Store): List<Keyword>
    fun getStoreListByWord(word: String): List<Store>
}