package com.chris.bullseye.system.controller

import com.chris.bullseye.common.config.AliConfig
import com.chris.bullseye.common.config.TencentConfig
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Chris
 * @date 2021-01-14 16:03
 */

@Api(value = "获取配置",tags = ["获取配置"])
@OperationLog("获取配置")
@RestController
@RequestMapping("/config")
class ConfigController(var aliConfig: AliConfig,var tencentConfig: TencentConfig) {


    @GetMapping("/getAliConfig")
    @ApiOperation(value = "阿里视频配置", notes = "阿里视频配置")
    fun getAliVideConfig(): JsonResult<Any> {
        return JsonResult.success(aliConfig,"获取成功！")
    }
    @GetMapping("/getTencentConfig")
    @ApiOperation(value = "腾讯配置", notes = "腾讯配置")
    fun getTencentConfig(): JsonResult<Any> {
        return JsonResult.success(tencentConfig,"获取成功！")
    }
}