package com.waitring.waitring.entity

import javax.persistence.*

/**
 * 메뉴 엔티티
 */
@Entity
class Menu(name: String, price: Int, store: Store, image: String? = null, detail: String? = "") : BaseEntity() {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false)
    var name = name // 메뉴명
        protected set

    @Column(nullable = false)
    var price = price // 메뉴 가격
        protected set

    var detail = detail // 메뉴 설명
        protected set

    @Column(columnDefinition = "TEXT")
    var image = image // 메뉴 이미지
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    var store = store // 메뉴가 속한 가게
        protected set

    override fun toString(): String {
        return "Menu(id=$id, name=$name, price=$price, detail=$detail, image=$image, store=$store)"
    }
}
