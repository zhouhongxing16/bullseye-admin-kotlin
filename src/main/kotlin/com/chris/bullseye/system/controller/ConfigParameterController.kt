package com.chris.bullseye.system.controller

import com.chris.bullseye.system.pojo.ConfigParameter
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.service.*

import com.chris.bullseye.system.service.ConfigParameterService
import com.chris.bullseye.common.utils.AuthUtil
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 */
@Api(value = "系统配置",tags = ["系统配置"])
@OperationLog("系统配置")
@RestController
@RequestMapping("/configparameter")
class ConfigParameterController(
        val configParameterService: ConfigParameterService,
        val jsonResult: JsonResult<ConfigParameter>
) {

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: ConfigParameter): JsonResult<ConfigParameter> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return configParameterService.add(obj)
    }
}