package com.chris.bullseye.system.controller

import com.chris.bullseye.system.pojo.Department
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.service.*
import com.chris.bullseye.common.utils.AuthUtil

import com.chris.bullseye.system.service.DepartmentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 11:24
 */
@Api(value = "部门科室",tags = ["部门科室"])
@OperationLog("部门/科室")
@RestController
@RequestMapping("/department")
class DepartmentController(
        val departmentService: DepartmentService,
        val jsonResult: JsonResult<Department>
){

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: Department): JsonResult<Department> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return departmentService.add(obj)
    }

    @OperationLog("(web)获取科室/部门")
    @PostMapping("/getWebDepartmentList")
    @ApiOperation(value = "(web)获取科室/部门", notes = "(web)获取科室/部门")
    @ApiImplicitParam(name = "(web)获取科室/部门", value = "")
    fun getWebDepartmentList(@RequestBody params: MutableMap<String, String?>): JsonResult<Department> {
        params["status"] = "1"
        var list = departmentService.getListByParams(params)
        return jsonResult.success(list,"查询成功！")
    }
}