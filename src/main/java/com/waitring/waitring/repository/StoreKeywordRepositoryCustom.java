package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.StoreKeyword;

import java.util.List;

public interface StoreKeywordRepositoryCustom {
    List<StoreKeyword> getKeywordByStores(List<Store> stores);
}