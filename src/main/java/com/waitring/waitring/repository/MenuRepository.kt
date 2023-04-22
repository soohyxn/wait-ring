package com.waitring.waitring.repository

import com.waitring.waitring.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long>