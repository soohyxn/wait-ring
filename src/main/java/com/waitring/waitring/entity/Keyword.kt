package com.waitring.waitring.entity

import javax.persistence.GeneratedValue
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * 키워드 엔티티
 */
@Entity
class Keyword(name: String) : BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var name = name // 키워드명
        protected set

    override fun toString(): String {
        return "Keyword(id=$id, name=$name)"
    }
}