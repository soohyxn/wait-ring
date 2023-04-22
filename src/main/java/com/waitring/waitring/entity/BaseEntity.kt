package com.waitring.waitring.entity

import javax.persistence.EntityListeners
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import org.springframework.data.annotation.LastModifiedDate
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * 공통 필드 엔티티
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
        @CreatedDate
        protected var createdAt : LocalDateTime? = null // 생성 일자

        @LastModifiedDate
        protected var updatedAt : LocalDateTime? = null // 수정 일자

        override fun toString(): String {
                return "BaseEntity(createdAt=$createdAt, updatedAt=$updatedAt)"
        }
}