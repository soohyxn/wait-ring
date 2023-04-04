package com.waitring.waitring.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waitring.waitring.dto.keyword.KeywordInfo;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreInfo;
import com.waitring.waitring.dto.store.StoreInput;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.StoreKeyword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(source = "image", target = "image", qualifiedByName = "imageToImage")
    @Mapping(source = "storeKeywords", target = "keywords")
    StoreInfo storeToStoreInfo(Store store);

    @Mapping(source = "store.image", target = "images", qualifiedByName = "imageToImages")
    StoreDetailInfo storeToStoreDetailInfo(Store store, List<Menu> menus, List<Keyword> keywords);

    @Mapping(source = "images", target = "image", qualifiedByName = "imagesToImage")
    @Mapping(target = "waitingFlag", defaultValue = "false")
    @Mapping(target = "reserveFlag", defaultValue = "false")
    Store storeInputToStore(StoreInput storeInput);

    @Mapping(source = "storeKeyword.keyword.id", target = "id")
    @Mapping(source = "storeKeyword.keyword.name", target = "name")
    KeywordInfo storeKeywordToKeywordInfo(StoreKeyword storeKeyword);

    List<StoreInfo> storeListToStoreInfoList(List<Store> stores);

    @Named("imageToImages")
    default String[] imageToImages(String s) throws JsonProcessingException {
        return objectMapper.readValue(s, String[].class);
    }

    @Named("imageToImage")
    default String imageToImage(String s) throws JsonProcessingException {
        return objectMapper.readValue(s, String[].class)[0];
    }

    @Named("imagesToImage")
    default String imagesToImage(String[] list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }
}
