package com.waitring.waitring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 회원 엔티티
 */

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String email; // 이메일
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private Date birthday; // 생년월일
    private Integer point; // 포인트 점수
}
