package com.chris.bullseye.system.mapper

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.MenuDto
import com.chris.bullseye.system.pojo.Menu
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date2020 12 07 16:59
 */
@Mapper
interface MenuMapper:MPBaseMapper<Menu> {
    fun getAllMenus(): List<MenuDto>

    fun getMenusByAccountId(map: Map<String, String?>): List<MenuDto>

    fun getMenusByRoleId(map: Map<String, String?>): List<MenuDto>

    fun getDtoListByParams(map: MutableMap<String, String?>): List<MenuDto>

    fun getListByPage(page: Page<MenuDto>, map: MutableMap<String, String?>): IPage<MenuDto>
}