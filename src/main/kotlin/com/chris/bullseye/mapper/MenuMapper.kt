package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.dto.MenuDto
import com.chris.bullseye.pojo.Menu
import org.apache.ibatis.annotations.Mapper

/**
 * @author Chris
 * @date2020 12 07 16:59
 */
@Mapper
interface MenuMapper:BaseMapper<Menu> {
    fun getAllMenus(): List<MenuDto>

    fun getMenusByAccountId(map: Map<String, String?>): List<MenuDto>

    fun getMenusByRoleId(map: Map<String, String?>): List<MenuDto>

    fun getDtoListByParams(map: MutableMap<String, String?>): List<MenuDto>
}