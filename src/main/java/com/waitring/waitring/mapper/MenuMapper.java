package com.waitring.waitring.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waitring.waitring.dto.menu.MenuInput;
import com.waitring.waitring.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);
    ObjectMapper objectMapper = new ObjectMapper();

    Menu menuInputToMenu(MenuInput menuRequest);
}
