package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    Store storeDetailInfoToStore(StoreDetailInfo storeDetailInfo);
    StoreDetailInfo storeToStoreDetailInfo(Store store);
}
