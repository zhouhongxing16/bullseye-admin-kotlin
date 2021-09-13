package com.chris.bullseye.controller

import com.chris.bullseye.pojo.RoleMenuFunction
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil

import com.chris.bullseye.service.RoleMenuFunctionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:50
 */
@Api(value = "角色菜单功能",tags = ["角色菜单功能"])
@OperationLog("角色菜单功能")
@RestController
@RequestMapping("/rolemenufunction")
class RoleMenuFunctionController(
        val roleMenuFunctionService: RoleMenuFunctionService,
        jsonResult: JsonResult<RoleMenuFunction>
) : BaseController<RoleMenuFunction>(jsonResult) {
         override fun service(): BaseService<RoleMenuFunction> {
        return roleMenuFunctionService
    }
}