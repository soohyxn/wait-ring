package com.waitring.waitring.service

import com.waitring.waitring.dto.store.StoreInput
import com.waitring.waitring.entity.Keyword
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.entity.Store
import com.waitring.waitring.entity.StoreKeyword
import com.waitring.waitring.repository.KeywordRepository
import com.waitring.waitring.repository.StoreKeywordRepository
import com.waitring.waitring.repository.StoreRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class StoreServiceTest {

    private val storeRepository: StoreRepository = mockk(relaxed = true)
    private val storeKeywordRepository: StoreKeywordRepository = mockk(relaxed = true)
    private val keywordRepository: KeywordRepository = mockk(relaxed = true)
    private val storeService = spyk(StoreService(storeRepository, storeKeywordRepository, keywordRepository))

    /**
     * 테스트 데이터 생성
     */
    private fun generateStore() = Store(
            name = "고든램지 버거",
            areaDong = "신천동",
            areaDetail = "서울 송파구 올림픽로 300 롯데월드몰 B1층",
            image = "[\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-22.jpg?w=1600&cbr=1&q=90&fit=max\"," +
                    "\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max\"," +
                    "\"https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F01%2FHypebeast-check-gordon-ramsay-burger-korean-restaurant-info-23.jpg?w=1600&cbr=1&q=90&fit=max\"]",
            openTime = "10:00",
            closeTime = "20:30",
            closeDay = "매주 셋째주 월요일",
            waitingFlag = true,
            reserveFlag = false
    )

    private fun generateMenu(store: Store) = Menu(
            store = store,
            name = "헬스 키친 버거",
            price = 31000,
            detail = "모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리",
            image = "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max"
    )

    private fun generateKeyword() = Keyword(name = "양식")

    private fun generateStoreKeyword(store: Store, keyword: Keyword) = StoreKeyword(
            store = store,
            keyword = keyword
    )

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
        val store = generateStore()
        val keyword = generateKeyword()
        val storeInput = generateStoreInput()
        every { storeRepository.save<Store>(any()) } returns store
        every { keywordRepository.findByIdOrNull(any()) } returns keyword
        every { storeKeywordRepository.save<StoreKeyword>(any()) } returns generateStoreKeyword(store, keyword)

        // when
        val addStore = storeService.addStore(storeInput)

        // then
        assertNotNull(addStore)

        // verify
        verify { storeRepository.save<Store>(any()) }
        verify { keywordRepository.findByIdOrNull(any()) }
        verify { storeKeywordRepository.save<StoreKeyword>(any()) }
    }

    @Test
    @DisplayName("가게 상세 조회")
    fun storeDetailInfo() {
        // given
        val store = generateStore()
        every { storeRepository.findByIdOrNull(any()) } returns store
        every { storeRepository.getMenusByStore(any()) } returns listOf(generateMenu(store))
        every { storeRepository.getKeywordsByStore(any()) } returns listOf(generateKeyword())

        // when
        val storeDetailInfo = storeService.getStoreDetail(1L)

        // then
        assertNotNull(storeDetailInfo)
        assertThat(storeDetailInfo.menus).isNotEmpty()
        assertThat(storeDetailInfo.keywords).isNotEmpty()

        // verify
        verify { storeRepository.findByIdOrNull(any()) }
        verify { storeRepository.getMenusByStore(any()) }
        verify { storeRepository.getKeywordsByStore(any()) }
    }

    @Test
    @DisplayName("검색어로 가게 조회")
    fun storeListByWord() {
        // given
        every { storeRepository.getStoreListByWord(any()) } returns listOf(generateStore())

        // when
        val storeInfoList = storeService.getStoreListByWord("버거")

        // then
        assertNotNull(storeInfoList)
        assertThat(storeInfoList.size).isEqualTo(1)

        // verify
        verify { storeRepository.getStoreListByWord(any()) }
    }
}