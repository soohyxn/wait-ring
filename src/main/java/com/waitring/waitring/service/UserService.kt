package com.waitring.waitring.service

import com.waitring.waitring.repository.UserRepository
import com.waitring.waitring.mapper.UserMapper
import org.springframework.security.crypto.password.PasswordEncoder
import com.waitring.waitring.dto.user.UserInput
import com.waitring.waitring.dto.user.UserLogin
import java.lang.IllegalStateException
import com.waitring.waitring.dto.user.UserInfo
import com.waitring.waitring.entity.User
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    companion object {
        private val log = LoggerFactory.getLogger(UserService::class.java)
        private val mapper = Mappers.getMapper(UserMapper::class.java)
    }

    /**
     * 회원가입
     * @param userInput 입력받은 회원 정보
     */
    fun addUser(userInput: UserInput): User {
        val user = mapper.userInputToUser(userInput)
        val addUser = userRepository.save(user)
        log.info("addUser: $addUser")
        return addUser
    }

    /**
     * 로그인
     * @param userLogin 입력받은 회원 정보
     */
    fun login(userLogin: UserLogin): User {
        val user = userRepository.findByEmail(userLogin.email!!) ?: throw IllegalStateException("해당 이메일을 사용하는 회원이 없습니다.")
        check(passwordEncoder.matches(userLogin.password, user.password)) { "비밀번호가 일치하지 않습니다." }
        log.info("login: $user")
        return user
    }

    /**
     * 회원조회
     * @param id 회원Id
     */
    fun getUser(id: Long): UserInfo {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalStateException("회원이 존재하지 않습니다.")
        val userInfo = mapper.userToUserInfo(user)
        log.info("getUser: $userInfo")
        return userInfo
    }
}