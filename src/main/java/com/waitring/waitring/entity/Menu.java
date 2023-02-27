package com.waitring.waitring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * 메뉴 엔티티
 */

@Entity
@Getter
@ToString(exclude = "store")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name; // 메뉴명
    private Integer price; // 메뉴 가격
    private String detail; // 메뉴 설명
    @Column(columnDefinition = "TEXT")
    private String image; // 메뉴 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store; // 메뉴가 속한 가게
}
