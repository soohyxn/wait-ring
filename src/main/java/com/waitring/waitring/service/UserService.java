package com.waitring.waitring.service;

import com.waitring.waitring.dto.user.UserInput;
import com.waitring.waitring.entity.User;
import com.waitring.waitring.mapper.UserMapper;
import com.waitring.waitring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * 회원가입
     * @param userInput 입력받은 회원 정보
     */
    public User addUser(UserInput userInput) {
        User user = userMapper.INSTANCE.userInputToUser(userInput);
        User addUser = userRepository.save(user);
        log.info("addUser: " + addUser);
        return addUser;
    }
}