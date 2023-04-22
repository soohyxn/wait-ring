package com.waitring.waitring

import com.waitring.waitring.entity.*
import javax.persistence.EntityManager
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

/**
 * 테스트 데이터
 */
@Component
@Profile("!test")
class InitDB(private val initService: InitService) {

    @PostConstruct
    fun init() {
        initService.dbInit()
    }

    @Component
    @Transactional
    class InitService(private val em: EntityManager) {

        @Transactional
        fun dbInit() {
            /**
             * 회원 테스트 데이터
             */
            val user1 = User(
                    email = "user1@waitring.com",
                    password = BCryptPasswordEncoder().encode("1234"),
                    nickname = "유저1",
                    point = 1234
            )
            em.persist(user1)

            /**
             * 가게 테스트 데이터
             */
            val store1 = Store(
                    name = "고든램지 버거",
                    areaDong = "신천동",
                    areaDetail = "서울 송파구 올림픽로 300 롯데월드몰 B1층",
                    image = "[\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max\"," +
                            "\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max\"," +
                            "\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max\"]",
                    openTime = "10:00",
                    closeTime ="20:30",
                    closeDay = "매주 셋째주 월요일",
                    waitingFlag = true,
                    reserveFlag = false
            )
            em.persist(store1)

            val store2 = Store(
                    name = "호랑이식당 롯데영등포점",
                    areaDong = "영등포동",
                    areaDetail = "서울특별시 영등포구 경인로 846 1층",
                    image = "[\"https://www.withbuyer.com/news/photo/202101/20946_11658_1837.jpg\"," +
                            "\"https://fastly.4sqi.net/img/general/600x600/1031332_xMRWHJI6gyx67OWao007x9QJHCRrwZXtvVikBSWLnZs.jpg\"," +
                            "\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnCsNulDAFudjACXelVM748SzRVsIQW2-MNQ&usqp=CAU\"]",
                    openTime = "10:00",
                    closeTime = "20:30",
                    closeDay = "매주 셋째주 월요일",
                    waitingFlag = true,
                    reserveFlag = true
            )
            em.persist(store2)

            val store3 = Store(
                    name = "후쿠오카함바그 롯데백화점노원",
                    areaDong = "상계동",
                    areaDetail = "서울특별시 노원구 동일로 1414",
                    image = "[\"https://t1.daumcdn.net/cfile/tistory/252170375909AB6931\"," +
                            "\"https://www.gimhae.go.kr/CmsMultiFile/view.do?multifileId=MF00002744&idx=15896&s=800x800\"," +
                            "\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6awLHGWLGuC7HDu80-FZBtwWW9-WIwOW0RQ&usqp=CAU\"]",
                    openTime = "10:00",
                    closeTime = "20:30",
                    closeDay = "매주 셋째주 월요일",
                    waitingFlag = true,
                    reserveFlag = true
            )
            em.persist(store3)

            val store4 = Store(
                    name = "별미곱창",
                    areaDong = "방이동",
                    areaDetail = "서울특별시 송파구 올림픽로32길 22",
                    image = "[\"https://mp-seoul-image-production-s3.mangoplate.com/209802/83465_1631076696663_63542?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/1250968_1651396898827190.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/1272306_1606457666085316.jpg\"]",
                    openTime = "11:00",
                    closeTime = "05:00",
                    closeDay = "매주 일요일",
                    waitingFlag = false,
                    reserveFlag = false
            )
            em.persist(store4)

            val store5 = Store(
                    name = "우미노미",
                    areaDong = "당산동",
                    areaDetail = "서울특별시 영등포구 당산로 180 신우빌딩 1F",
                    image = "[\"https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500466267\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/405162/513273_1597229296822_10244\"," +
                            "\"https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500463779\"]",
                    openTime = "11:30",
                    closeTime = "20:00",
                    closeDay = "매주 월요일, 일요일",
                    waitingFlag = false,
                    reserveFlag = false
            )
            em.persist(store5)

            /**
             * 메뉴 테스트 데이터
             */
            val menu1 = Menu(
                    store = store1,
                    name = "헬스 키친 버거",
                    price = 31000,
                    detail = "모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리",
                    image = "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max"
            )
            em.persist(menu1)

            val menu2 = Menu(
                    store = store1,
                    name = "야드버드 버거",
                    price = 27000,
                    detail = "닭다리살, 네쉬빌 소스, 고추장 마요, 모타모, 버터 레터스, 콜슬로",
                    image = "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max"
            )
            em.persist(menu2)

            val menu3 = Menu(
                    store = store1,
                    name = "포레스트 버거",
                    price = 33000,
                    detail = "그뤼예르 치즈, 머쉬룸 라구, 포르치니 마요네즈, 루꼴라, 유정란(서니사이드업)",
                    image = "https://blog.kakaocdn.net/dn/rB0Wj/btrapGjFjND/5zK1gXkq5Hsm24n4yE6lvk/img.jpg"
            )
            em.persist(menu3)

            val menu4 = Menu(
                    store = store2,
                    name = "호면",
                    price = 10500,
                    detail = "24시간 우려낸 탄탄멘\n 베이스 육수에 한국의 맛을 담은 면 요리",
                    image = "https://fastly.4sqi.net/img/general/600x600/1031332_xMRWHJI6gyx67OWao007x9QJHCRrwZXtvVikBSWLnZs.jpg"
            )
            em.persist(menu4)

            val menu5 = Menu(
                    store = store2,
                    name = "마제면",
                    price = 10500,
                    detail = "일본식 마제면에 두툼한 차슈 한덩이가 올라간 매콤 짭쪼름한 풍미가 인상적인 면 요리",
                    image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnCsNulDAFudjACXelVM748SzRVsIQW2-MNQ&usqp=CAU"
            )
            em.persist(menu5)

            val menu6 = Menu(
                    store = store2,
                    name = "차슈덮밥",
                    price = 10500,
                    detail = "특제소스로 오랜시간 익힌 부드러운 차슈가 올라간 덮밥",
                    image = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA5MDhfOTgg%2FMDAxNTk5NTM1MjQ2MzAw.pgyfMYr9IGSAc9b_2hVbMYnKQpvS2gjMGiGwGYQ3MSwg.VfbdZBtiP4Kz4q5IrvyZEpVy7fayOXm-4jYjaY_45M4g.JPEG.jiwongone%2FIMG_6306.jpg&type=a340"
            )
            em.persist(menu6)

            val menu7 = Menu(
                    store = store3,
                    name = "오리지널 한우 함바그",
                    price = 13900,
                    detail = "Small",
                    image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuAmGxad5NjtCXj6MupWPg-jBQpxsP6MMDoxDzmCYxT1GfJwwPeAnvijP7UzvrdpYGuoE&usqp=CAU"
            )
            em.persist(menu7)

            val menu8 = Menu(
                    store = store3,
                    name = "에그치즈 한우 함바그",
                    price = 15900,
                    detail = "Small",
                    image = "https://photo.akmall.com/image4/goods/78/32/74/79/78327479_M_350.jpg"
            )
            em.persist(menu8)

            val menu9 = Menu(
                    store = store3,
                    name = "스테이크동",
                    price = 13000,
                    detail = "스테이크 덮밥소스",
                    image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQl7NTqm9Yet_q0V1b6kOF1RLBAfQDM1BY-IqXPb4v41Ot2MIg-gIqGqUIRySgA632jPQ8&usqp=CAU"
            )
            em.persist(menu9)

            val menu10 = Menu(
                    store = store4,
                    name = "모듭곱창",
                    price = 25000,
                    detail = "1인분 200g",
                    image = "https://mp-seoul-image-production-s3.mangoplate.com/209802/83465_1631076696663_63542?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80"
            )
            em.persist(menu10)

            val menu11 = Menu(
                    store = store4,
                    name = "소곱창",
                    price = 25000,
                    detail = "1인분 200g",
                    image = "https://mp-seoul-image-production-s3.mangoplate.com/1272306_1606457666085316.jpg"
            )
            em.persist(menu11)

            val menu12 = Menu(
                    store = store4,
                    name = "볶음밥",
                    price = 4000,
                    image = "https://mp-seoul-image-production-s3.mangoplate.com/2091186_1640675023029693.jpg"
            )
            em.persist(menu12)

            val menu13 = Menu(
                    store = store5,
                    name = "카이센동",
                    price = 17000,
                    detail = "해산물을 이용한 훗카이도식 덮밥입니다. 구성은 시장상황에 따라서 변동될 수 있습니다.",
                    image = "https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/405162/894765_1640500466267"
            )
            em.persist(menu13)

            val menu14 = Menu(
                    store = store5,
                    name = "카이센동 모듬",
                    price = 33000,
                    detail = "해산물을 이용한 훗카이도식 덮밥입니다. 구성은 시장상황에 따라서 변동될 수 있습니다.",
                    image = "https://mp-seoul-image-production-s3.mangoplate.com/405162/513273_1597229296822_10244"
            )
            em.persist(menu14)

            val menu15 = Menu(
                    store = store5,
                    name = "차돌스키야키정식",
                    price = 17000,
                    detail = "차돌박이를 이용한 일본식 소고기 전골입니다. 1인메뉴 한상차림입니다.",
                    image = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA3MDZfNzAg%2FMDAxNjI1NTQxMjQ5MTg2.BS7k_kjP1mO2iSLwj2dF6NLzG0ZRT9tWxXGhnY0Z6E4g.ctXqSH2PQ_gDWV6ctRUH4lR16hlOCj29dQMM2bozNz0g.JPEG.gumingii%2FKakaoTalk_20210706_114123668.jpg&type=sc960_832"
            )
            em.persist(menu15)

            /**
             * 키워드 테스트 데이터
             */
            val keyword1 = Keyword(name = "프리미엄")
            em.persist(keyword1)

            val keyword2 = Keyword(name = "양식")
            em.persist(keyword2)

            val keyword3 = Keyword(name = "버거")
            em.persist(keyword3)

            val keyword4 = Keyword(name = "깨끗한")
            em.persist(keyword4)

            val keyword5 = Keyword(name = "일식")
            em.persist(keyword5)

            val keyword6 = Keyword(name = "라멘")
            em.persist(keyword6)

            val keyword7 = Keyword(name = "함바그")
            em.persist(keyword7)

            val keyword8 = Keyword(name = "한식")
            em.persist(keyword8)

            val keyword9 = Keyword(name = "곱창")
            em.persist(keyword9)

            val keyword10 = Keyword(name = "안주")
            em.persist(keyword10)

            val keyword11 = Keyword(name = "돈부리")
            em.persist(keyword11)

            val keyword12 = Keyword(name = "벤또")
            em.persist(keyword12)

            /**
             * 가게_키워드 테스트 데이터
             */
            val storeKeyword1 = StoreKeyword(store = store1, keyword = keyword1)
            em.persist(storeKeyword1)

            val storeKeyword2 = StoreKeyword(store = store1, keyword = keyword2)
            em.persist(storeKeyword2)

            val storeKeyword3 = StoreKeyword(store = store1, keyword = keyword3)
            em.persist(storeKeyword3)

            val storeKeyword4 = StoreKeyword(store = store2, keyword = keyword4)
            em.persist(storeKeyword4)

            val storeKeyword5 = StoreKeyword(store = store2, keyword = keyword5)
            em.persist(storeKeyword5)

            val storeKeyword6 = StoreKeyword(store = store2, keyword = keyword6)
            em.persist(storeKeyword6)

            val storeKeyword7 = StoreKeyword(store = store3, keyword = keyword4)
            em.persist(storeKeyword7)

            val storeKeyword8 = StoreKeyword(store = store3, keyword = keyword5)
            em.persist(storeKeyword8)

            val storeKeyword9 = StoreKeyword(store = store3, keyword = keyword7)
            em.persist(storeKeyword9)

            val storeKeyword10 = StoreKeyword(store = store4, keyword = keyword8)
            em.persist(storeKeyword10)

            val storeKeyword11 = StoreKeyword(store = store4, keyword = keyword9)
            em.persist(storeKeyword11)

            val storeKeyword12 = StoreKeyword(store = store4, keyword = keyword10)
            em.persist(storeKeyword12)

            val storeKeyword13 = StoreKeyword(store = store5, keyword = keyword5)
            em.persist(storeKeyword13)

            val storeKeyword14 = StoreKeyword(store = store5, keyword = keyword11)
            em.persist(storeKeyword14)

            val storeKeyword15 = StoreKeyword(store = store5, keyword = keyword12)
            em.persist(storeKeyword15)
        }
    }
}