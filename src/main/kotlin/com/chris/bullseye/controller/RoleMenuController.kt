package com.chris.bullseye.controller

import com.chris.bullseye.pojo.RoleMenu
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil

import com.chris.bullseye.service.RoleMenuService
import org.springframework.beans.factory.annotation.Autowired
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-21 17:08
 */
@Api(value = "角色菜单",tags = ["角色菜单"])
@OperationLog("角色菜单")
@RestController
@RequestMapping("/rolemenu")
class RoleMenuController(
        val roleMenuService: RoleMenuService,
        jsonResult: JsonResult<RoleMenu>
) : BaseController<RoleMenu>(jsonResult) {
         override fun service(): BaseService<RoleMenu> {
        return roleMenuService
    }


    @OperationLog("角色菜单授权")
    @ApiOperation(value = "角色菜单授权")
    @ApiImplicitParam(name = "角色菜单授权")
    @PostMapping("/createRoleMenu/{roleId}")
    fun createRoleMenu(@RequestBody list: List<String?>,@PathVariable roleId:String): JsonResult<RoleMenu> {
        return roleMenuService.createRoleMenu(list,roleId)
    }
}