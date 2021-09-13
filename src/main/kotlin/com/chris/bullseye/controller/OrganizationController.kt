package com.chris.bullseye.controller

import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.Organization
import com.chris.bullseye.pojo.Role
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.OrganizationService
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
 * @date 2021-01-04 14:21
 */

@Api(value = "组织",tags = ["组织"])
@OperationLog("组织")
@RestController
@RequestMapping("/organization")
class OrganizationController(var organizationService: OrganizationService,jsonResult: JsonResult<Organization>):BaseController<Organization>(jsonResult) {
    override fun service(): BaseService<Organization> {
        return organizationService
    }


    @OperationLog("(web)获取登录页面组织选择")
    @PostMapping("/getWebOrganizations")
    @ApiOperation(value = "(web)获取登录页面组织选择", notes = "(web)获取登录页面组织选择")
    @ApiImplicitParam(name = "(web)获取登录页面组织选择", value = "")
    fun getWebOrganizations(): JsonResult<Organization>{
        val map: MutableMap<String, String?> = HashMap(2)
        map["status"] = "1"
        var list = organizationService.getListByParams(map)
        return jsonResult.successList(list,"查询成功！")
    }



    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: Organization): JsonResult<Organization> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }
}