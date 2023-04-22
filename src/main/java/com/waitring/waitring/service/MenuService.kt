package com.waitring.waitring.service

import com.waitring.waitring.dto.menu.MenuInput
import com.waitring.waitring.entity.Menu
import com.waitring.waitring.mapper.MenuMapper
import com.waitring.waitring.repository.MenuRepository
import com.waitring.waitring.repository.StoreRepository
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MenuService(private val storeRepository: StoreRepository, private val menuRepository: MenuRepository) {

    companion object {
        private val log = LoggerFactory.getLogger(MenuService::class.java)
        private val mapper = Mappers.getMapper(MenuMapper::class.java)
    }

    /**
     * 메뉴 등록
     * @param id        메뉴가 속한 가게Id
     * @param menuInput 입력받은 메뉴 정보
     */
    fun addMenu(id: Long, menuInput: MenuInput): Menu {
        val store = storeRepository.findByIdOrNull(id) ?: throw IllegalStateException("가게(id=$id)가 존재하지 않습니다.")
        val menu = mapper.menuInputToMenu(menuInput, store)
        val addMenu = menuRepository.save(menu)
        log.info("addMenu: $addMenu")
        return addMenu
    }
}