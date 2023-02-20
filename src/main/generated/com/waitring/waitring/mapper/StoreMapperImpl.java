package com.waitring.waitring.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreDetailInfo.StoreDetailInfoBuilder;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.Store.StoreBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-20T14:55:37+0900",
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

        try {
            store.image( mapImages( storeDetailInfo.getImages() ) );
        }
        catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
        store.id( storeDetailInfo.getId() );
        store.name( storeDetailInfo.getName() );
        store.areaDong( storeDetailInfo.getAreaDong() );
        store.areaDetail( storeDetailInfo.getAreaDetail() );
        store.keyword( storeDetailInfo.getKeyword() );
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

        try {
            storeDetailInfo.images( mapImage( store.getImage() ) );
        }
        catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
        storeDetailInfo.id( store.getId() );
        storeDetailInfo.name( store.getName() );
        storeDetailInfo.areaDong( store.getAreaDong() );
        storeDetailInfo.areaDetail( store.getAreaDetail() );
        storeDetailInfo.keyword( store.getKeyword() );
        storeDetailInfo.openTime( store.getOpenTime() );
        storeDetailInfo.closeTime( store.getCloseTime() );
        storeDetailInfo.closeDay( store.getCloseDay() );
        storeDetailInfo.waitingFlag( store.getWaitingFlag() );
        storeDetailInfo.reserveFlag( store.getReserveFlag() );

        return storeDetailInfo.build();
    }
}
