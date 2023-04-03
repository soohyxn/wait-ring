package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepositoryCustom {
    List<Menu> getMenusByStore(Store store);
    List<Keyword> getKeywordsByStore(Store store);
}