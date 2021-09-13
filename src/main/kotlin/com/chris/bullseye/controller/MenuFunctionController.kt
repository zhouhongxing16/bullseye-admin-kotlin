package com.chris.bullseye.controller

import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.Major
import com.chris.bullseye.pojo.MenuFunction
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.MenuFunctionService
import com.chris.bullseye.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
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
class MenuFunctionController(var menuFunctionService: MenuFunctionService,jsonResult: JsonResult<MenuFunction>) : BaseController<MenuFunction>(jsonResult)  {

    override fun service(): BaseService<MenuFunction> {
        return menuFunctionService
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: MenuFunction): JsonResult<MenuFunction> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }
}