package com.waitring.waitring.repository;

import com.waitring.waitring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}