package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.mapper.RoleMenuMapper
import com.chris.bullseye.pojo.RoleMenu
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020-12-10 15:57
 */
@Service
class RoleMenuService(var roleMenuMapper: RoleMenuMapper) :BaseService<RoleMenu>() {

    override fun getMapper(): BaseMapper<RoleMenu> {
        return roleMenuMapper
    }


    fun createRoleMenu(list: List<String?>,roleId:String): JsonResult<RoleMenu> {
        val result = JsonResult<RoleMenu>()
        result.status = HttpStatus.OK.value()
        if (list.isNullOrEmpty()) {
            result.success = false
            result.message = "错误！数据不能为空！"
        } else {
            val params: MutableMap<String, String?> = HashMap(2)
            params["roleId"] = roleId
            roleMenuMapper.deleteByParams(params)
            for (menuId in list) {
                var roleMenu = RoleMenu()
                roleMenu.roleId = roleId
                roleMenu.menuId = menuId
                roleMenu.id = null
                roleMenu.status = 1
                roleMenuMapper.insert(roleMenu)
            }
            result.success=true
            result.message  ="菜单授权成功！"
        }
        return result
    }
}