package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Menu> getMenusByStore(Store store);
    List<Keyword> getKeywordsByStore(Store store);
    List<Store> getStoreListByWord(String word);
}