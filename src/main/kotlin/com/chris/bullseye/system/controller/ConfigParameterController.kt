package com.chris.bullseye.system.controller

import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.ConfigParameter
import com.chris.bullseye.system.service.ConfigParameterService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    /*//增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: ConfigParameter): JsonResult<ConfigParameter> {
        var user  = AuthUtil.getCurrentUser()
        obj.creatorId = user!!.id
        return configParameterService.add(obj)
    }*/
}