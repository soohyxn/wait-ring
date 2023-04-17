package com.waitring.waitring.mapper

import com.waitring.waitring.dto.user.UserInput
import com.waitring.waitring.dto.user.UserInfo
import com.waitring.waitring.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface UserMapper {
    @Mappings(
            Mapping(source = "password", target = "password", qualifiedByName = ["passwordEncode"]),
            Mapping(target = "point", defaultValue = "0")
    )
    fun userInputToUser(userInput: UserInput): User

    fun userToUserInfo(user: User): UserInfo

    companion object {
        @JvmStatic
        @Named("passwordEncode")
        fun passwordEncode(s: String) = BCryptPasswordEncoder().encode(s)
    }
}