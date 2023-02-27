package com.waitring.waitring.service;

import com.waitring.waitring.dto.menu.MenuInfo;
import com.waitring.waitring.entity.Menu;
import com.waitring.waitring.entity.Store;
import com.waitring.waitring.mapper.MenuMapper;
import com.waitring.waitring.repository.MenuRepository;
import com.waitring.waitring.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    /**
     * 메뉴 등록
     * @param id 메뉴가 속한 가게Id
     * @param menuInput 입력받은 메뉴 정보
     */
    public Menu addMenu(Long id, MenuInfo menuInput) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalStateException("가게(id=" + id + ")가 존재하지 않습니다."));
        Menu menu = menuMapper.INSTANCE.menuInputToMenu(menuInput, store);
        Menu addMenu = menuRepository.save(menu);
        log.info("addMenu: " + addMenu);
        return addMenu;
    }
}