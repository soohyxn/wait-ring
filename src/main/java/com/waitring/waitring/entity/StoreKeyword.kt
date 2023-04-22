package com.waitring.waitring.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * 가게_키워드 엔티티
 */
@Entity
class StoreKeyword(store: Store, keyword: Keyword) : BaseEntity() {
    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    @JsonIgnore
    var store = store // 가게
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false, updatable = false)
    var keyword = keyword // 키워드
        protected set

    override fun toString(): String {
        return "StoreKeyword(id=$id, store=$store, keyword=$keyword)"
    }
}
