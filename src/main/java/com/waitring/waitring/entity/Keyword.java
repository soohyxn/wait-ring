package com.waitring.waitring.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 키워드 엔티티
 */

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name; // 키워드명
}
