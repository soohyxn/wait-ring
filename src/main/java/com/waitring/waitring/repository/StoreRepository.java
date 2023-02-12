package com.waitring.waitring.repository;

import com.waitring.waitring.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}