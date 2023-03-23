package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Store;

import java.util.Optional;

public interface StoreRepositoryCustom {
    Optional<Store> getStoreById(Long id);
}