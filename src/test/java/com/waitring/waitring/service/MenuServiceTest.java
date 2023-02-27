package com.waitring.waitring.service;

import com.waitring.waitring.dto.menu.MenuInfo;
import com.waitring.waitring.dto.menu.MenuInput;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.repository.MenuRepository;
import com.waitring.waitring.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    /**
     * 테스트 데이터 생성
     */
    Store generateStore() {
        return Store.builder()
                .id(1L)
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
    }

    Menu generateMenu(Store store) {
        return Menu.builder()
                .id(2L)
                .store(store)
                .name("헬스 키친 버거")
                .price(31000)
                .detail("모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리")
                .image("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max")
                .build();
    }

    MenuInput generateMenuInput() {
        return MenuInput.builder()
                .name("헬스 키친 버거")
                .price(31000)
                .detail("모차렐라 치즈, 로스티드 할라피뇨&토마토, 아보카도, 할리피뇨 아이뮬리")
                .image("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2021%2F11%2FGordon-ramsay-burger-korean-open-date-official-info-02.jpg?q=75&w=800&cbr=1&fit=max")
                .build();
    }

    @Test
    @DisplayName("메뉴 등록")
    void addStore() {
        // given
        Store store = generateStore();
        Menu menu = generateMenu(store);
        given(storeRepository.findById(store.getId())).willReturn(Optional.of(store));
        given(menuRepository.save(any(Menu.class))).willReturn(menu);

        // when
        Menu addMenu = menuService.addMenu(store.getId(), generateMenuInput());

        // then
        assertNotNull(addMenu);
        assertThat(addMenu.getId()).isEqualTo(2L);

        // verify
        verify(storeRepository).findById(any(Long.class));
        verify(menuRepository).save(any(Menu.class));
    }
}