package com.chris.bullseye.system.controller

import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.MenuFunction
import com.chris.bullseye.system.service.MenuFunctionService
import com.chris.bullseye.common.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Chris
 * @date 2020-12-23 15:57
 */
@Api(tags = ["菜单功能"], produces = "菜单功能")
@OperationLog("菜单功能")
@RestController
@RequestMapping("/menufunction")
class MenuFunctionController(var menuFunctionService: MenuFunctionService)  {


/*
    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: MenuFunction): JsonResult<MenuFunction> {
        var user  = AuthUtil.getCurrentUser()
        obj.creatorId = user!!.id
        obj.creatorName = user!!.name
        return menuFunctionService.add(obj)
    }*/
}