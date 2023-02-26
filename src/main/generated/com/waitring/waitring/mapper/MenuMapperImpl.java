package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.menu.MenuInput;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Menu.MenuBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T21:16:16+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu menuInputToMenu(MenuInput menuRequest) {
        if ( menuRequest == null ) {
            return null;
        }

        MenuBuilder menu = Menu.builder();

        menu.id( menuRequest.getId() );
        menu.store( menuRequest.getStore() );
        menu.name( menuRequest.getName() );
        menu.price( menuRequest.getPrice() );
        menu.detail( menuRequest.getDetail() );
        menu.image( menuRequest.getImage() );

        return menu.build();
    }
}
