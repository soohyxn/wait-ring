package com.waitring.waitring;

import com.waitring.waitring.entity.Store;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 테스트 데이터
 */

@Component
@RequiredArgsConstructor
@Profile("!test")
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        @Transactional
        public void dbInit() {

            // 가게 테스트 데이터
            Store store1 = Store.builder()
                    .name("고든램지 버거")
                    .areaDong("신천동")
                    .areaDetail("서울 송파구 올림픽로 300 롯데월드몰 B1층")
                    .keyword("프리미엄, 양식, 버거")
                    .imageUrl("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max")
                    .openTime("10:00")
                    .closeTime("20:30")
                    .closeDay("매주 셋째주 월요일")
                    .waitingFlag(true)
                    .reserveFlag(false)
                    .build();
            em.persist(store1);

            Store store2 = Store.builder()
                    .name("호랑이식당 롯데영등포점")
                    .areaDong("영등포동")
                    .areaDetail("서울특별시 영등포구 경인로 846 1층")
                    .keyword("깨끗한, 일식, 라멘")
                    .imageUrl("https://www.withbuyer.com/news/photo/202101/20946_11658_1837.jpg")
                    .openTime("10:00")
                    .closeTime("20:30")
                    .closeDay("매주 셋째주 월요일")
                    .waitingFlag(true)
                    .reserveFlag(true)
                    .build();
            em.persist(store2);

            Store store3 = Store.builder()
                    .name("후쿠오카함바그 롯데백화점노원")
                    .areaDong("상계동")
                    .areaDetail("서울특별시 노원구 동일로 1414")
                    .keyword("깨끗한, 일식, 함바그")
                    .imageUrl("https://t1.daumcdn.net/cfile/tistory/252170375909AB6931")
                    .openTime("10:00")
                    .closeTime("20:30")
                    .closeDay("매주 셋째주 월요일")
                    .waitingFlag(true)
                    .reserveFlag(true)
                    .build();
            em.persist(store3);

            Store store4 = Store.builder()
                    .name("별미곱창")
                    .areaDong("방이동")
                    .areaDetail("서울특별시 송파구 올림픽로32길 22")
                    .keyword("한식, 곱창, 안주")
                    .imageUrl( "https://mp-seoul-image-production-s3.mangoplate.com/209802/83465_1631076696663_63542?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80")
                    .openTime("11:00")
                    .closeTime("05:00")
                    .closeDay("매주 일요일")
                    .waitingFlag(false)
                    .reserveFlag(false)
                    .build();
            em.persist(store4);

            Store store5 = Store.builder()
                    .name("우미노미")
                    .areaDong("당산동")
                    .areaDetail("서울특별시 영등포구 당산로 180 신우빌딩 1F")
                    .keyword("일식, 돈부리, 벤또")
                    .imageUrl("https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500466267")
                    .openTime("11:30")
                    .closeTime("20:00")
                    .closeDay("매주 월요일, 일요일")
                    .waitingFlag(false)
                    .reserveFlag(false)
                    .build();
            em.persist(store5);
        }

    }

}