package com.waitring.waitring.repository

import com.waitring.waitring.entity.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Store, Long>, StoreRepositoryCustom {
    fun findByNameContainingOrAreaDongContaining(name: String, areaDong: String): List<Store>
}