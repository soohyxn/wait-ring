package com.waitring.waitring.entity

import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * 키워드 엔티티
 */
@Entity
class Keyword(name :String?) : BaseEntity() {
    @Id
    @GeneratedValue
    private val id: Long? = null

    @Column(unique = true)
    private val name = name // 키워드명
}