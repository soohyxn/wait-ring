package com.waitring.waitring.mapper

import com.waitring.waitring.dto.menu.MenuInput
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.entity.Store
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface MenuMapper {
    @Mappings(
            Mapping(source = "menuInput.name", target = "name"),
            Mapping(source = "menuInput.price", target = "price"),
            Mapping(source = "menuInput.detail", target = "detail"),
            Mapping(source = "menuInput.image", target = "image"),
            Mapping(source = "store", target = "store")
    )
    fun menuInputToMenu(menuInput: MenuInput, store: Store): Menu
}
