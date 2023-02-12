package com.waitring.waitring.service;

import com.waitring.waitring.dto.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.mapper.StoreMapper;
import com.waitring.waitring.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreMapper storeMapper;

    @InjectMocks
    private StoreService storeService;

    /**
     * 가게 테스트 데이터 생성
     */
    Store generateStore() {
        return Store.builder()
                .id(1L)
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
    }

    StoreDetailInfo generateStoreDetailInfo() {
        return StoreDetailInfo.builder()
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
    }

    @Test
    @DisplayName("가게 상세 정보 조회")
    void getStoreDetailInfo() {
        // given
        Store store = generateStore();
        given(storeRepository.findById(store.getId())).willReturn(Optional.of(store));
        StoreDetailInfo storeDetailInfo = generateStoreDetailInfo();
        given(storeMapper.storeToStoreDetailInfo(store)).willReturn(storeDetailInfo);

        // when
        StoreDetailInfo storeResponse = storeService.getShopInfoDetail(store.getId());

        // then
        assertNotNull(storeResponse);
        verify(storeRepository).findById(any(Long.class));
        verify(storeMapper).storeToStoreDetailInfo(any(Store.class));
    }
}