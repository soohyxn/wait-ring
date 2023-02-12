package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreDetailInfo storeToStoreDetailInfo(Store store);
}
