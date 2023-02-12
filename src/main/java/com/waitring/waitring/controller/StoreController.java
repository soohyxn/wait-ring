package com.waitring.waitring.controller;

import com.waitring.waitring.dto.StoreDetailInfo;
import com.waitring.waitring.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 상세 정보 조회 API
     * @param id 가게Id
     * @return ResponseEntity 200 OK, 가게 상세 정보
     *                        500 INTERNAL SERVER ERROR, 에러 메세지
     */
    @GetMapping("/{id}")
    public ResponseEntity getStoreDetailInfo(@PathVariable("id") Long id) {
        StoreDetailInfo store = storeService.getShopInfoDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(store);
    }
}
