package com.waitring.waitring;

import com.waitring.waitring.entity.Menu;
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
                    .image("[\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max\"," +
                            "\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max\"," +
                            "\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max\"]")
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
                    .image("[\"https://www.withbuyer.com/news/photo/202101/20946_11658_1837.jpg\"," +
                            "\"https://fastly.4sqi.net/img/general/600x600/1031332_xMRWHJI6gyx67OWao007x9QJHCRrwZXtvVikBSWLnZs.jpg\"," +
                            "\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnCsNulDAFudjACXelVM748SzRVsIQW2-MNQ&usqp=CAU\"]")
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
                    .image("[\"https://t1.daumcdn.net/cfile/tistory/252170375909AB6931\"," +
                            "\"https://www.gimhae.go.kr/CmsMultiFile/view.do?multifileId=MF00002744&idx=15896&s=800x800\"," +
                            "\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6awLHGWLGuC7HDu80-FZBtwWW9-WIwOW0RQ&usqp=CAU\"]")
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
                    .image("[\"https://mp-seoul-image-production-s3.mangoplate.com/209802/83465_1631076696663_63542?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/1250968_1651396898827190.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/1272306_1606457666085316.jpg\"]")
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
                    .image("[\"https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500466267\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/405162/513273_1597229296822_10244\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500463779\"]")
                    .openTime("11:30")
                    .closeTime("20:00")
                    .closeDay("매주 월요일, 일요일")
                    .waitingFlag(false)
                    .reserveFlag(false)
                    .build();
            em.persist(store5);

            // 메뉴 테스트 데이터
            Menu menu1 = Menu.builder()
                    .store(store1)
                    .name("헬스 키친 버거")
                    .price(31000)
                    .detail("모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리")
                    .image("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max")
                    .build();
            em.persist(menu1);

            Menu menu2 = Menu.builder()
                    .store(store1)
                    .name("야드버드 버거")
                    .price(27000)
                    .detail("닭다리살, 네쉬빌 소스, 고추장 마요, 모타모, 버터 레터스, 콜슬로")
                    .image("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max")
                    .build();
            em.persist(menu2);

            Menu menu3 = Menu.builder()
                    .store(store1)
                    .name("포레스트 버거")
                    .price(33000)
                    .detail("그뤼예르 치즈, 머쉬룸 라구, 포르치니 마요네즈, 루꼴라, 유정란(서니사이드업)")
                    .image("https://blog.kakaocdn.net/dn/rB0Wj/btrapGjFjND/5zK1gXkq5Hsm24n4yE6lvk/img.jpg")
                    .build();
            em.persist(menu3);

            Menu menu4 = Menu.builder()
                    .store(store2)
                    .name("호면")
                    .price(10500)
                    .detail("24시간 우려낸 탄탄멘\n 베이스 육수에 한국의 맛을 담은 면 요리")
                    .image("https://fastly.4sqi.net/img/general/600x600/1031332_xMRWHJI6gyx67OWao007x9QJHCRrwZXtvVikBSWLnZs.jpg")
                    .build();
            em.persist(menu4);

            Menu menu5 = Menu.builder()
                    .store(store2)
                    .name("마제면")
                    .price(10500)
                    .detail("일본식 마제면에 두툼한 차슈 한덩이가 올라간 매콤 짭쪼름한 풍미가 인상적인 면 요리")
                    .image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnCsNulDAFudjACXelVM748SzRVsIQW2-MNQ&usqp=CAU")
                    .build();
            em.persist(menu5);

            Menu menu6 = Menu.builder()
                    .store(store2)
                    .name("차슈덮밥")
                    .price(10500)
                    .detail("특제소스로 오랜시간 익힌 부드러운 차슈가 올라간 덮밥")
                    .image("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA5MDhfOTgg%2FMDAxNTk5NTM1MjQ2MzAw.pgyfMYr9IGSAc9b_2hVbMYnKQpvS2gjMGiGwGYQ3MSwg.VfbdZBtiP4Kz4q5IrvyZEpVy7fayOXm-4jYjaY_45M4g.JPEG.jiwongone%2FIMG_6306.jpg&type=a340")
                    .build();
            em.persist(menu6);

            Menu menu7 = Menu.builder()
                    .store(store3)
                    .name("오리지널 한우 함바그")
                    .price(13900)
                    .detail("Small")
                    .image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuAmGxad5NjtCXj6MupWPg-jBQpxsP6MMDoxDzmCYxT1GfJwwPeAnvijP7UzvrdpYGuoE&usqp=CAU")
                    .build();
            em.persist(menu7);

            Menu menu8 = Menu.builder()
                    .store(store3)
                    .name("에그치즈 한우 함바그")
                    .price(15900)
                    .detail("Small")
                    .image("https://photo.akmall.com/image4/goods/78/32/74/79/78327479_M_350.jpg")
                    .build();
            em.persist(menu8);

            Menu menu9 = Menu.builder()
                    .store(store3)
                    .name("스테이크동")
                    .price(13000)
                    .detail("스테이크 덮밥소스")
                    .image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQl7NTqm9Yet_q0V1b6kOF1RLBAfQDM1BY-IqXPb4v41Ot2MIg-gIqGqUIRySgA632jPQ8&usqp=CAU")
                    .build();
            em.persist(menu9);

            Menu menu10 = Menu.builder()
                    .store(store4)
                    .name("모듭곱창")
                    .price(25000)
                    .detail("1인분 200g")
                    .image("https://mp-seoul-image-production-s3.mangoplate.com/209802/83465_1631076696663_63542?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80")
                    .build();
            em.persist(menu10);

            Menu menu11 = Menu.builder()
                    .store(store4)
                    .name("소곱창")
                    .price(25000)
                    .detail("1인분 200g")
                    .image("https://mp-seoul-image-production-s3.mangoplate.com/1272306_1606457666085316.jpg")
                    .build();
            em.persist(menu11);

            Menu menu12 = Menu.builder()
                    .store(store4)
                    .name("볶음밥")
                    .price(4000)
                    .detail("")
                    .image("https://mp-seoul-image-production-s3.mangoplate.com/2091186_1640675023029693.jpg")
                    .build();
            em.persist(menu12);

            Menu menu13 = Menu.builder()
                    .store(store5)
                    .name("카이센동")
                    .price(17000)
                    .detail("해산물을 이용한 훗카이도식 덮밥입니다. 구성은 시장상황에 따라서 변동될 수 있습니다.")
                    .image("https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500466267")
                    .build();
            em.persist(menu13);

            Menu menu14 = Menu.builder()
                    .store(store5)
                    .name("카이센동 모듬")
                    .price(33000)
                    .detail("해산물을 이용한 훗카이도식 덮밥입니다. 구성은 시장상황에 따라서 변동될 수 있습니다.")
                    .image("https://mp-seoul-image-production-s3.mangoplate.com/405162/513273_1597229296822_10244")
                    .build();
            em.persist(menu14);

            Menu menu15 = Menu.builder()
                    .store(store5)
                    .name("차돌스키야키정식")
                    .price(17000)
                    .detail("차돌박이를 이용한 일본식 소고기 전골입니다. 1인메뉴 한상차림입니다.")
                    .image("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA3MDZfNzAg%2FMDAxNjI1NTQxMjQ5MTg2.BS7k_kjP1mO2iSLwj2dF6NLzG0ZRT9tWxXGhnY0Z6E4g.ctXqSH2PQ_gDWV6ctRUH4lR16hlOCj29dQMM2bozNz0g.JPEG.gumingii%2FKakaoTalk_20210706_114123668.jpg&type=sc960_832")
                    .build();
            em.persist(menu15);
        }

    }

}