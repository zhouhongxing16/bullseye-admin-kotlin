package com.chris.bullseye.system.controller

import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.Organization
import com.chris.bullseye.system.service.OrganizationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Chris
 * @date 2021-01-04 14:21
 */

@Api(value = "组织",tags = ["组织"])
@OperationLog("组织")
@RestController
@RequestMapping("/organization")
class OrganizationController(var organizationService: OrganizationService){


    @OperationLog("(web)获取登录页面组织选择")
    @PostMapping("/getWebOrganizations")
    @ApiOperation(value = "(web)获取登录页面组织选择", notes = "(web)获取登录页面组织选择")
    @ApiImplicitParam(name = "(web)获取登录页面组织选择", value = "")
    fun getWebOrganizations(): JsonResult<Any>{
        val map: MutableMap<String, String?> = HashMap(2)
        map["status"] = "1"
        var list = organizationService.getListByParams(map)
        return JsonResult.success(list,"查询成功！")
    }



    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: Organization): JsonResult<Organization> {
        var user  = AuthUtil.getCurrentUser()
        obj.creatorId = user!!.id
        obj.creatorName = user!!.name
        return organizationService.add(obj)
    }
}