package com.waitring.waitring.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waitring.waitring.dto.keyword.KeywordInfo;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreInfo;
import com.waitring.waitring.dto.store.StoreInput;
import com.waitring.waitring.entity.Keyword;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.StoreKeyword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(source = "image", target = "image", qualifiedByName = "imageToImage")
    StoreInfo storeToStoreInfo(Store store);

    @Mapping(source = "store.image", target = "images", qualifiedByName = "imageToImages")
    StoreDetailInfo storeToStoreDetailInfo(Store store, List<Keyword> keywords);

    @Mapping(source = "images", target = "image", qualifiedByName = "imagesToImage")
    @Mapping(target = "waitingFlag", defaultValue = "false")
    @Mapping(target = "reserveFlag", defaultValue = "false")
    Store storeInputToStore(StoreInput storeInput);

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

    @Named("storeKeywordsToKeywords")
    default List<KeywordInfo> storeKeywordsToKeywords(List<StoreKeyword> storeKeywords) throws JsonProcessingException {
        return storeKeywords.stream().map(s -> new KeywordInfo(s.getKeyword().getId(), s.getKeyword().getName())).collect(Collectors.toList());
    }
}
