package com.waitring.waitring.service

import com.waitring.waitring.dto.store.StoreDetailInfo
import com.waitring.waitring.dto.store.StoreInfo
import com.waitring.waitring.dto.store.StoreInput
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.entity.Store
import com.waitring.waitring.entity.StoreKeyword
import com.waitring.waitring.mapper.StoreMapper
import com.waitring.waitring.repository.KeywordRepository
import com.waitring.waitring.repository.StoreKeywordRepository
import com.waitring.waitring.repository.StoreRepository
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StoreService(private val storeRepository: StoreRepository, private val storeKeywordRepository: StoreKeywordRepository, private val keywordRepository: KeywordRepository) {

    companion object {
        private val log = LoggerFactory.getLogger(StoreService::class.java)
        private val mapper = Mappers.getMapper(StoreMapper::class.java)
    }

    /**
     * 가게 등록
     * @param storeInput 입력받은 가게 정보
     */
    fun addStore(storeInput: StoreInput): Store {
        val store = mapper.storeInputToStore(storeInput)

        // 가게 저장
        val addStore = storeRepository.save(store)
        log.info("addStore: $addStore")

        // 가게_키워드 저장
        storeInput.keywords?.forEach { id ->
            val keyword = keywordRepository.findByIdOrNull(id) ?:  throw IllegalStateException("키워드(id=$id)가 존재하지 않습니다.")
            val addStoreKeyword: StoreKeyword = storeKeywordRepository.save(StoreKeyword(store = addStore, keyword = keyword))
            log.info("addStoreKeyword: $addStoreKeyword")
        }
        return addStore
    }

    /**
     * 가게 상세 조회
     * @param id 가게Id
     * @return 가게 상세 정보
     */
    @Transactional(readOnly = true)
    fun getStoreDetail(id: Long): StoreDetailInfo {
        val store = storeRepository.findByIdOrNull(id) ?: throw IllegalStateException("가게(id=$id)가 존재하지 않습니다.")
        val menus = storeRepository.getMenusByStore(store)
        val keywords = storeRepository.getKeywordsByStore(store)
        val storeDetailInfo = mapper.storeToStoreDetailInfo(store, menus, keywords)
        log.info("getStoreDetail: $storeDetailInfo")
        return storeDetailInfo
    }

    /**
     * 검색어로 가게 조회
     * @param word 검색어
     * @return 검색된 가게 정보 리스트
     */
    @Transactional(readOnly = true)
    fun getStoreListByWord(word: String): List<StoreInfo> {
        val stores = storeRepository.getStoreListByWord(word)
        val storeInfos = mapper.storeListToStoreInfoList(stores)
        log.info("getStoreListByWord: $storeInfos")
        return storeInfos
    }
}