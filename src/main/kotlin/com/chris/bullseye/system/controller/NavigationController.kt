package com.chris.bullseye.system.controller

import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.Navigation
import com.chris.bullseye.system.service.NavigationService
import com.chris.bullseye.common.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:25
 */
@Api(value = "导航栏", tags = ["导航栏"])
@OperationLog("导航栏")
@RestController
@RequestMapping("/navigation")
class NavigationController( val navigationService: NavigationService){


    @OperationLog("(web)获取网页导航栏")
    @PostMapping("/getWebNavigation")
    @ApiOperation(value = "(web)获取网页导航栏", notes = "(web)获取网页导航栏")
    fun getWebNavigation(): JsonResult<Any> {
        val map: MutableMap<String, String?> = HashMap(2)
        map["status"] = "1"
        var list = navigationService.getListByParams(map)
        return JsonResult.success(list, "查询成功！")
    }

    /* //增加
     @ApiOperation(value = "创建方法", notes = "创建")
     @OperationLog("创建方法")
     @PostMapping("/create")
     fun create(@RequestBody obj: Navigation): JsonResult<Navigation> {
         var user  = AuthUtil.getCurrentUser()
         obj.creatorId = user!!.id
         obj.creatorName = user!!.name
         return navigationService.add(obj)
     }*/
}