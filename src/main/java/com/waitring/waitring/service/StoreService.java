package com.waitring.waitring.service;

import com.waitring.waitring.dto.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.mapper.StoreMapper;
import com.waitring.waitring.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper shopMapper;

    /**
     * 가게 정보 조회
     *
     * @param id 가게Id
     * @return 가게 상세 정보
     */
    public StoreDetailInfo getStoreInfoDetail(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("가게(id=" + id + ")가존재하지 않습니다."));
        return shopMapper.storeToStoreDetailInfo(store);
    }
}