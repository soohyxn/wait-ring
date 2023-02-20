package com.waitring.waitring.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(source = "images", target = "image")
    Store storeDetailInfoToStore(StoreDetailInfo storeDetailInfo);
    @Mapping(source = "image", target = "images")
    StoreDetailInfo storeToStoreDetailInfo(Store store);

    default String[] mapImage(String s) throws JsonProcessingException {
        return objectMapper.readValue(s, String[].class);
    }

    default String mapImages(String[] list) throws JsonProcessingException {
        return objectMapper.writeValueAsString(list);
    }
}
