package com.waitring.waitring.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.waitring.waitring.dto.keyword.KeywordInfo
import com.waitring.waitring.dto.store.StoreDetailInfo
import com.waitring.waitring.dto.store.StoreInfo
import com.waitring.waitring.dto.store.StoreInput
import com.waitring.waitring.entity.Keyword
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.entity.Store
import com.waitring.waitring.entity.StoreKeyword
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface StoreMapper {

    @Mappings(
            Mapping(source = "image", target = "image", qualifiedByName = ["imageToImage"]),
            Mapping(source = "storeKeywords", target = "keywords")
    )
    fun storeToStoreInfo(store: Store): StoreInfo

    @Mapping(source = "store.image", target = "images", qualifiedByName = ["imageToImages"])
    fun storeToStoreDetailInfo(store: Store, menus: List<Menu>, keywords: List<Keyword>): StoreDetailInfo

    @Mappings(
            Mapping(source = "images", target = "image", qualifiedByName = ["imagesToImage"]),
            Mapping(target = "waitingFlag", defaultValue = "false"),
            Mapping(target = "reserveFlag", defaultValue = "false")
    )
    fun storeInputToStore(storeInput: StoreInput): Store

    @Mappings(
            Mapping(source = "storeKeyword.keyword.id", target = "id"),
            Mapping(source = "storeKeyword.keyword.name", target = "name")
    )
    fun storeKeywordToKeywordInfo(storeKeyword: StoreKeyword): KeywordInfo

    fun storeListToStoreInfoList(stores: List<Store>): List<StoreInfo>

    companion object {
        val objectMapper = ObjectMapper()

        @JvmStatic
        @Named("imageToImages")
        fun imageToImages(s: String): List<String> {
            return objectMapper.readValue(s, Array<String>::class.java).toList()
        }

        @JvmStatic
        @Named("imageToImage")
        fun imageToImage(s: String): String {
            return objectMapper.readValue(s, Array<String>::class.java)[0]
        }

        @JvmStatic
        @Named("imagesToImage")
        fun imagesToImage(list: List<String>): String {
            return objectMapper.writeValueAsString(list)
        }
    }
}
