package com.waitring.waitring.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.waitring.waitring.dto.store.StoreDetailInfo;
import com.waitring.waitring.dto.store.StoreDetailInfo.StoreDetailInfoBuilder;
import com.waitring.waitring.dto.store.StoreInfo;
import com.waitring.waitring.dto.store.StoreInfo.StoreInfoBuilder;
import com.waitring.waitring.dto.store.StoreInput;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.entity.Store.StoreBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T10:20:02+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreInfo storeToStoreInfo(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreInfoBuilder storeInfo = StoreInfo.builder();

        try {
            storeInfo.image( imageToImage( store.getImage() ) );
        }
        catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
        storeInfo.id( store.getId() );
        storeInfo.name( store.getName() );
        storeInfo.areaDong( store.getAreaDong() );
        storeInfo.keyword( store.getKeyword() );
        storeInfo.waitingFlag( store.getWaitingFlag() );
        storeInfo.reserveFlag( store.getReserveFlag() );

        return storeInfo.build();
    }

    @Override
    public StoreDetailInfo storeToStoreDetailInfo(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDetailInfoBuilder storeDetailInfo = StoreDetailInfo.builder();

        try {
            storeDetailInfo.images( imageToImages( store.getImage() ) );
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

    @Override
    public Store storeInputToStore(StoreInput storeInput) {
        if ( storeInput == null ) {
            return null;
        }

        StoreBuilder store = Store.builder();

        try {
            store.image( imagesToImage( storeInput.getImages() ) );
        }
        catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }
        store.name( storeInput.getName() );
        store.areaDong( storeInput.getAreaDong() );
        store.areaDetail( storeInput.getAreaDetail() );
        store.keyword( storeInput.getKeyword() );
        store.openTime( storeInput.getOpenTime() );
        store.closeTime( storeInput.getCloseTime() );
        store.closeDay( storeInput.getCloseDay() );
        store.waitingFlag( storeInput.getWaitingFlag() );
        store.reserveFlag( storeInput.getReserveFlag() );

        return store.build();
    }

    @Override
    public List<StoreInfo> storeListToStoreInfoList(List<Store> stores) {
        if ( stores == null ) {
            return null;
        }

        List<StoreInfo> list = new ArrayList<StoreInfo>( stores.size() );
        for ( Store store : stores ) {
            list.add( storeToStoreInfo( store ) );
        }

        return list;
    }
}
