package com.waitring.waitring.service;

import com.waitring.waitring.dto.user.UserInfo;
import com.waitring.waitring.dto.user.UserInput;
import com.waitring.waitring.dto.user.UserLogin;
import com.waitring.waitring.entity.User;
import com.waitring.waitring.mapper.UserMapper;
import com.waitring.waitring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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

    /**
     * 로그인
     * @param userLogin 입력받은 회원 정보
     */
    public User login(UserLogin userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail()).orElseThrow(() -> new IllegalStateException("해당 이메일을 사용하는 회원이 없습니다."));
        if (!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        log.info("login: " + user);
        return user;
    }

    /**
     * 회원조회
     * @param id 회원Id
     */
     public UserInfo getUser(Long id) {
         User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("회원이 존재하지 않습니다."));
         UserInfo userInfo = userMapper.INSTANCE.userToUserInfo(user);
         log.info("getUser: " + userInfo);
         return userInfo;
     }
}