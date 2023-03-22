package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepositoryCustom {
    Optional<Store> getStoreById(Long id);
    List<Keyword> getKeywordsByStore(Store store);
}