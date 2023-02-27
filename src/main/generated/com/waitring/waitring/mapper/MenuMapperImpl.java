package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.menu.MenuInput;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Menu.MenuBuilder;
import com.waitring.waitring.entity.Store;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T10:20:02+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu menuInputToMenu(MenuInput menuInput, Store store) {
        if ( menuInput == null && store == null ) {
            return null;
        }

        MenuBuilder menu = Menu.builder();

        if ( menuInput != null ) {
            menu.name( menuInput.getName() );
            menu.price( menuInput.getPrice() );
            menu.detail( menuInput.getDetail() );
            menu.image( menuInput.getImage() );
        }
        if ( store != null ) {
            menu.store( store );
        }

        return menu.build();
    }
}
