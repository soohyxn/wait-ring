package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Store;

public interface StoreRepositoryCustom {
    Store getStoreById(Long id);
}