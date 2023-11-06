package com.waitring.waitring.common.config

import javax.persistence.EntityManager
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.PersistenceContext

@Configuration
class QuerydslConfig(@PersistenceContext private val entityManager: EntityManager) {

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}