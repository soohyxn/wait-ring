package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    List<Store> findByNameContainingOrAreaDongContaining(String name, String areaDong);
}