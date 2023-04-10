package com.waitring.waitring.repository

import com.waitring.waitring.entity.Keyword
import org.springframework.data.jpa.repository.JpaRepository

interface KeywordRepository : JpaRepository<Keyword, Long>