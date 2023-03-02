package com.waitring.waitring.mapper;

import com.waitring.waitring.dto.user.UserInput;
import com.waitring.waitring.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "password", target = "password", qualifiedByName = "passwordEncode")
    @Mapping(target = "point", defaultValue = "0")
    User userInputToUser(UserInput userInput);

    @Named("passwordEncode")
    default String passwordEncode(String s) {
        return new BCryptPasswordEncoder().encode(s);
    }
}
