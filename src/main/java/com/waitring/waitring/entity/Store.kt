package com.waitring.waitring.entity

import javax.persistence.*

/**
 * 가게 엔티티
 */
@Entity
class Store(name: String, areaDong: String, areaDetail: String, openTime: String, closeTime: String, waitingFlag: Boolean, reserveFlag: Boolean,
            image: String? = null, closeDay: String? = null, locationLat: String? = null, locationLng: String? = null) : BaseEntity() {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false)
    var name = name // 가게명
        protected set

    @Column(nullable = false)
    var areaDong = areaDong // 가게 동네 위치
        protected set

    @Column(nullable = false)
    var areaDetail = areaDetail // 가게 상세 주소
        protected set

    @Column(columnDefinition = "TEXT")
    var image = image // 가게 이미지
        protected set

    @Column(nullable = false)
    var openTime = openTime // 영업 오픈시간
        protected set

    @Column(nullable = false)
    var closeTime = closeTime // 영업 마감시간
        protected set

    var closeDay = closeDay // 휴무일
        protected set

    @Column(nullable = false)
    var waitingFlag = waitingFlag // 웨이팅 가능 여부
        protected set

    @Column(nullable = false)
    var reserveFlag = reserveFlag // 예약 가능 여부
        protected set

    var locationLat = locationLat // 가게 위도 정보
        protected set

    var locationLng = locationLng // 가게 경도 정보
        protected set

    @OneToMany(mappedBy = "store", cascade = [CascadeType.ALL], orphanRemoval = true)
    private var _storeKeywords: MutableList<StoreKeyword> = mutableListOf() // 가게_키워드 리스트
    val storeKeywords: List<StoreKeyword>
        get() = _storeKeywords.toList()

    override fun toString(): String {
        return "Store(id=$id, name=$name, areaDong=$areaDong, areaDetail=$areaDetail, image=$image, openTime=$openTime, closeTime=$closeTime, closeDay=$closeDay, waitingFlag=$waitingFlag, reserveFlag=$reserveFlag, locationLat=$locationLat, locationLng=$locationLng, storeKeywords=$storeKeywords)"
    }
}
