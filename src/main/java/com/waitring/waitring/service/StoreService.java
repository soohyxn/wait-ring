package com.waitring.waitring.service;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreInfo;
import com.waitring.waitring.dto.store.StoreInput;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.StoreKeyword;
import com.waitring.waitring.mapper.StoreMapper;
import com.waitring.waitring.repository.KeywordRepository;
import com.waitring.waitring.repository.StoreKeywordRepository;
import com.waitring.waitring.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreKeywordRepository storeKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final StoreMapper shopMapper;

    /**
     * 가게 등록
     * @param storeInput 입력받은 가게 정보
     */
    public Store addStore(StoreInput storeInput) {
        Store store = shopMapper.INSTANCE.storeInputToStore(storeInput);

        // 가게 저장
        Store addStore = storeRepository.save(store);
        log.info("addStore: " + addStore);

        // 가게_키워드 저장
        for(Long id : storeInput.getKeywords()) {
            Keyword keyword = keywordRepository.findById(id).orElseThrow(() -> new IllegalStateException("키워드(id=" + id + ")가 존재하지 않습니다."));
            StoreKeyword addStoreKeyword = storeKeywordRepository.save(StoreKeyword.builder().store(addStore).keyword(keyword).build());
            log.info("addStoreKeyword: " + addStoreKeyword);
        }

        return addStore;
    }

    /**
     * 가게 상세 조회
     * @param id 가게Id
     * @return 가게 상세 정보
     */
    @Transactional(readOnly = true)
    public StoreDetailInfo getStoreDetail(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException("가게(id=" + id + ")가 존재하지 않습니다."));
        List<Menu> menus = storeRepository.getMenusByStore(store);
        List<Keyword> keywords = storeRepository.getKeywordsByStore(store);

        StoreDetailInfo storeDetailInfo = shopMapper.INSTANCE.storeToStoreDetailInfo(store, menus, keywords);
        log.info("getStoreDetail: " + storeDetailInfo);
        return storeDetailInfo;
    }

    /**
     * 검색어로 가게 조회
     * @param word 검색어
     * @return 검색된 가게 정보 리스트
     */
    @Transactional(readOnly = true)
    public List<StoreInfo> getStoreListByWord(String word) {
        List<Store> stores = storeRepository.findByNameContainingOrAreaDongContaining(word, word);
        List<StoreKeyword> storeKeywords = storeKeywordRepository.getKeywordByStores(stores);

        // mapping
        List<StoreInfo> storeInfos = shopMapper.INSTANCE.storeListToStoreInfoList(stores);
        shopMapper.INSTANCE.setKeywords(storeInfos, storeKeywords);

        log.info("getStoreListByWord: " + storeInfos);
        return storeInfos;
    }
}