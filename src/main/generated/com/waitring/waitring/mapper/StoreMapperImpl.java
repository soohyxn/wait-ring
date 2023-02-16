package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreDetailInfo.StoreDetailInfoBuilder;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.Store.StoreBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-16T16:46:20+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public Store storeDetailInfoToStore(StoreDetailInfo storeDetailInfo) {
        if ( storeDetailInfo == null ) {
            return null;
        }

        StoreBuilder store = Store.builder();

        store.name( storeDetailInfo.getName() );
        store.areaDong( storeDetailInfo.getAreaDong() );
        store.areaDetail( storeDetailInfo.getAreaDetail() );
        store.keyword( storeDetailInfo.getKeyword() );
        store.imageUrl( storeDetailInfo.getImageUrl() );
        store.openTime( storeDetailInfo.getOpenTime() );
        store.closeTime( storeDetailInfo.getCloseTime() );
        store.closeDay( storeDetailInfo.getCloseDay() );
        store.waitingFlag( storeDetailInfo.getWaitingFlag() );
        store.reserveFlag( storeDetailInfo.getReserveFlag() );

        return store.build();
    }

    @Override
    public StoreDetailInfo storeToStoreDetailInfo(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDetailInfoBuilder storeDetailInfo = StoreDetailInfo.builder();

        storeDetailInfo.name( store.getName() );
        storeDetailInfo.areaDong( store.getAreaDong() );
        storeDetailInfo.areaDetail( store.getAreaDetail() );
        storeDetailInfo.keyword( store.getKeyword() );
        storeDetailInfo.imageUrl( store.getImageUrl() );
        storeDetailInfo.openTime( store.getOpenTime() );
        storeDetailInfo.closeTime( store.getCloseTime() );
        storeDetailInfo.closeDay( store.getCloseDay() );
        storeDetailInfo.waitingFlag( store.getWaitingFlag() );
        storeDetailInfo.reserveFlag( store.getReserveFlag() );

        return storeDetailInfo.build();
    }
}
