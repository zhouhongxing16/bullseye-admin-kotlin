package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.dto.response.MenuResponse
import com.chris.bullseye.system.mapper.MenuMapper
import com.chris.bullseye.system.pojo.Menu
import com.chris.bullseye.common.utils.AuthUtil
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

/**
 * @author Chris
 * @date 2020 12 10 15:40
 */
@Service
class MenuService(var menuMapper: MenuMapper): BaseService<Menu>() {

    override fun getMapper(): MPBaseMapper<Menu> {
       return menuMapper
    }


    fun getDtoListByParams(map: MutableMap<String, String?>): List<MenuResponse> {
        return menuMapper.getDtoListByParams(map)
    }

    fun getMenusByAccountId(): List<MenuResponse> {
        val user = AuthUtil.getCurrentUser()
        val map: MutableMap<String, String?> = HashMap(2)
        if (user != null) {
            map["accountId"] = user.id
            map["organizationId"] = user.organizationId
        }

        val menus: List<MenuResponse> = menuMapper.getMenusByAccountId(map)
        /*val menuList: MutableList<MenuResponse> = ArrayList<MenuResponse>()
        // 先找到所有的一级菜单
        for (menu in menus) {
            // 一级菜单没有pId
            if (StringUtils.isEmpty(menu.parentId)) {
                menuList.add(menu)
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (menu in menuList) {
            menu.children = menu.id?.let { getChild(it, menus) }
        }*/
        return menus
    }

    fun getMenusByRoleId(roleId: String): List<MenuResponse> {
        val map: MutableMap<String, String> = HashMap(2)
        map["roleId"] = roleId
        return menuMapper.getMenusByRoleId(map)
    }

    fun getAllMenus(): List<MenuResponse> {
        val menus: List<MenuResponse> = menuMapper.getAllMenus()
        val menuList: MutableList<MenuResponse> = ArrayList<MenuResponse>()

        // 先找到所有的一级菜单
        for (menu in menus) {
            // 一级菜单没有pId
            if (menu.parentId== null) {
                menuList.add(menu)
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (menu in menuList) {
            menu.children = menu.id?.let { getChild(it, menus) }
        }
        return menuList
    }


    private fun getChild(id: String, menuList: List<MenuResponse>): List<MenuResponse>? {
        // 子菜单
        val childList: MutableList<MenuResponse> = ArrayList<MenuResponse>()
        //遍历所有节点，将父级菜单ID与传过来的ID做比较
        for (menu in menuList) {
            if (!menu.parentId.isNullOrEmpty()) {
                if (menu.parentId == id) {
                    menu.children = menu.id?.let { getChild(it, menuList) }
                    childList.add(menu)
                }
            }
        }
        return if (childList.size == 0) {
            null
        } else childList
    }

}
