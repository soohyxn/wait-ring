package com.waitring.waitring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 가게 상세 정보
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetailInfo {

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
}
