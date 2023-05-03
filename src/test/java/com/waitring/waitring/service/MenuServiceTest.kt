package com.waitring.waitring.service

import com.waitring.waitring.dto.menu.MenuInput
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.entity.Store
import com.waitring.waitring.repository.MenuRepository
import com.waitring.waitring.repository.StoreRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@ExtendWith(MockitoExtension::class)
class MenuServiceTest {

    private val storeRepository: StoreRepository = mockk(relaxed = true)
    private val menuRepository: MenuRepository = mockk(relaxed = true)
    private val menuService = spyk(MenuService(storeRepository, menuRepository))

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
            closeTime ="20:30",
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

    private fun generateMenuInput() = MenuInput(
            name = "헬스 키친 버거",
            price = 31000,
            detail = "모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리",
            image = "https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max"
    )

    @Test
    @DisplayName("메뉴 등록")
    fun addMenu() {
        // given
        val store = generateStore()
        val menuInput = generateMenuInput()
        every { storeRepository.findByIdOrNull(any()) } returns store
        every { menuRepository.save<Menu>(any()) } returns generateMenu(store)

        // when
        val addMenu = menuService.addMenu(1L, menuInput)

        // then
        assertNotNull(addMenu)

        // verify
        verify { storeRepository.findByIdOrNull(any()) }
        verify { menuRepository.save<Menu>(any()) }
    }
}