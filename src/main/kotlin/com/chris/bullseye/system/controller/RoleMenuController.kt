package com.chris.bullseye.system.controller

import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.RoleMenu
import com.chris.bullseye.system.service.RoleMenuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-21 17:08
 */
@Api(value = "角色菜单", tags = ["角色菜单"])
@OperationLog("角色菜单")
@RestController
@RequestMapping("/rolemenu")
class RoleMenuController( val roleMenuService: RoleMenuService ) {


    @OperationLog("角色菜单授权")
    @ApiOperation(value = "角色菜单授权")
    @PostMapping("/createRoleMenu/{roleId}")
    fun createRoleMenu(@RequestBody list: List<String?>, @PathVariable roleId: String): JsonResult<RoleMenu> {
        return roleMenuService.createRoleMenu(list, roleId)
    }
}