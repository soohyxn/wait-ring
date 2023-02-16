package com.waitring.waitring.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 가게 엔티티
 */

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name; // 가게명
    private String areaDong; // 가게 동네 위치
    private String areaDetail; // 가게 상세 주소
    private String keyword; // 가게 키워드
    private String imageUrl; // 가게 이미지
    private String openTime; // 영업 오픈시간
    private String closeTime; // 영업 마감시간
    private String closeDay; // 휴무일
    private Boolean waitingFlag; // 웨이팅 가능 여부
    private Boolean reserveFlag; // 예약 가능 여부
    private String locationLat; // 가게 위도 정보
    private String locationLng; // 가게 경도 정보
}
