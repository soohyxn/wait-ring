package com.waitring.waitring.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waitring.waitring.dto.menu.MenuInfo;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "menuInput.name", target = "name")
    @Mapping(source = "menuInput.price", target = "price")
    @Mapping(source = "menuInput.detail", target = "detail")
    @Mapping(source = "menuInput.image", target = "image")
    @Mapping(source = "store", target = "store")
    Menu menuInputToMenu(MenuInfo menuInput, Store store);
}
