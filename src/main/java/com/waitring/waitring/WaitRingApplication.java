package com.waitring.waitring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WaitRingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaitRingApplication.class, args);
    }

}
