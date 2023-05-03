package com.waitring.waitring.controller

import com.google.gson.Gson
import com.waitring.waitring.dto.keyword.KeywordInfo
import com.waitring.waitring.dto.menu.MenuInfo
import com.waitring.waitring.dto.store.StoreDetailInfo
import com.waitring.waitring.dto.store.StoreInfo
import com.waitring.waitring.dto.store.StoreInput
import com.waitring.waitring.service.StoreService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@WebMvcTest(StoreController::class)
@MockBean(JpaMetamodelMappingContext::class)
@AutoConfigureMockMvc(addFilters = false)
class StoreControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var storeService: StoreService

    /**
     * 테스트 데이터 생성
     */
    private fun generateStoreDetailInfo(menus: List<MenuInfo>, keywords: List<KeywordInfo>) = StoreDetailInfo(
            id = 1L,
            name = "고든램지 버거",
            areaDong = "신천동",
            areaDetail = "서울 송파구 올림픽로 300 롯데월드몰 B1층",
            images = listOf(
                    "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max",
                    "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max",
                    "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max"),
            openTime = "10:00",
            closeTime = "20:30",
            closeDay = "매주 셋째주 월요일",
            waitingFlag = true,
            reserveFlag = false,
            menus = menus,
            keywords = keywords
    )

    private fun generateStoreInfo(keywords: List<KeywordInfo>) = StoreInfo(
            id = 1L,
            name = "고든램지 버거",
            areaDong = "신천동",
            image = "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max",
            waitingFlag = true,
            reserveFlag = false,
            keywords = keywords
    )

    private fun generateMenuInfo() =  MenuInfo(
            id = 2L,
            name = "헬스 키친 버거",
            price = 31000,
            detail = "모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리",
            image = "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max"
    )

    private fun generateKeywordInfo() = KeywordInfo(id = 3L, name = "양식")

    private fun generateStoreInput() = StoreInput(
            name = "고든램지 버거",
            areaDong = "신천동",
            areaDetail = "서울 송파구 올림픽로 300 롯데월드몰 B1층",
            images = listOf(
                    "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max",
                    "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max",
                    "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max"),
            openTime = "10:00",
            closeTime = "20:30",
            closeDay = "매주 셋째주 월요일",
            waitingFlag = true,
            reserveFlag = false,
            keywords = listOf(3L)
    )

    @Test
    @DisplayName("가게 등록")
    fun addStore() {
        // given
        val storeInput = generateStoreInput()

        // when
        val result = mockMvc.post("/store") {
            contentType = APPLICATION_JSON
            content = Gson().toJson(storeInput)
        }

        // then
        result.andExpect {
            status { isCreated() }
            jsonPath("$.result").value("가게 등록이 완료되었습니다.")
        }
    }

    @Test
    @DisplayName("가게 상세 조회")
    fun storeDetailInfo() {
            // given
            val storeDetailInfo = generateStoreDetailInfo(listOf(generateMenuInfo()), listOf(generateKeywordInfo()))
            given(storeService.getStoreDetail(any(Long::class.java))).willReturn(storeDetailInfo)

            // when
            val result = mockMvc.get("/store/1")

            // then
            result.andExpect {
                status { isOk() }
                jsonPath("$.id").value(1L)
                jsonPath("$.name").value("고든램지 버거")
                jsonPath("$.areaDong").value("신천동")
                jsonPath("$.areaDetail").value("서울 송파구 올림픽로 300 롯데월드몰 B1층")
                jsonPath("$.images.length()").value(3)
                jsonPath("$.openTime").value("10:00")
                jsonPath("$.closeTime").value("20:30")
                jsonPath("$.closeDay").value("매주 셋째주 월요일")
                jsonPath("$.waitingFlag").value(true)
                jsonPath("$.reserveFlag").value(false)
                jsonPath("$.menus.length()").value(1)
                jsonPath("$.keywords.length()").value(1)
            }
        }

    @Test
    @DisplayName("검색어로 가게 조회")
    fun storeListByWord() {
            // given
            val storeInfoList = listOf(generateStoreInfo(listOf(generateKeywordInfo())))
            given(storeService.getStoreListByWord("버거")).willReturn(storeInfoList)

            // when
            val result = mockMvc.get("/stores") {
                param("query", "버거")
            }

            // then
            result.andExpect {
                status { isOk() }
                jsonPath("$.id").value(1L)
                jsonPath("$.name").value("고든램지 버거")
                jsonPath("$.areaDong").value("신천동")
                jsonPath("$[0].image").isNotEmpty()
                jsonPath("$.waitingFlag").value(true)
                jsonPath("$.reserveFlag").value(false)
                jsonPath("$[0].keywords.length()").value(1)
            }
        }
}