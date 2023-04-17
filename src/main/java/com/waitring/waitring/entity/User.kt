package com.waitring.waitring.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * 회원 엔티티
 */
@Entity
class User(email: String?, password: String?, nickname: String?, point: Int?) : BaseEntity() {
    @Id
    @GeneratedValue
    val id: Long? = null

    val email = email // 이메일
    val password = password // 비밀번호
    val nickname = nickname // 닉네임
//    val birthday = birthday // 생년월일
    val point = point // 포인트 점수
}