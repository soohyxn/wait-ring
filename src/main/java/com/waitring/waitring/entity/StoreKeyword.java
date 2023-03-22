package com.waitring.waitring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 가게_키워드 엔티티
 */

@Entity
@Getter
@ToString(exclude = "store")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreKeyword extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", updatable = false)
    @JsonIgnore
    private Store store; // 가게

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", updatable = false)
    @JsonIgnore
    private Keyword keyword; // 키워드
}
