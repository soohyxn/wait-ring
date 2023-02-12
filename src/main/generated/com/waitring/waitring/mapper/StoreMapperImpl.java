package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.StoreDetailInfo;
import com.waitring.waitring.entity.Store;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-12T22:07:09+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDetailInfo storeToStoreDetailInfo(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDetailInfo storeDetailInfo = new StoreDetailInfo();

        return storeDetailInfo;
    }
}
