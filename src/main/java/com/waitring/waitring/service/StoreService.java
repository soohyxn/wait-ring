package com.waitring.waitring.service;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.mapper.StoreMapper;
import com.waitring.waitring.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper shopMapper;

    /**
     * 가게 등록
     * @param storeDetailInfo 입력받은 가게 정보
     */
    public Store addStore(StoreDetailInfo storeDetailInfo) {
        Store store = shopMapper.INSTANCE.storeDetailInfoToStore(storeDetailInfo);
        Store addStore = storeRepository.save(store);
        log.info("addStore: " + addStore);
        return addStore;
    }

    /**
     * 가게 상세 조회
     * @param id 가게Id
     * @return 가게 상세 정보
     */
    @Transactional(readOnly = true)
    public StoreDetailInfo getStoreInfoDetail(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException("가게(id=" + id + ")가 존재하지 않습니다."));
        StoreDetailInfo storeDetailInfo = shopMapper.INSTANCE.storeToStoreDetailInfo(store);
        log.info("getStoreInfoDetail: " + storeDetailInfo);
        return storeDetailInfo;
    }
}