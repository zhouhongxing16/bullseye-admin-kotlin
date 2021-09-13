package com.chris.bullseye.controller

import com.chris.bullseye.pojo.Department
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.DictionaryType
import com.chris.bullseye.pojo.Major
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil

import com.chris.bullseye.service.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
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
        jsonResult: JsonResult<Department>
) : BaseController<Department>(jsonResult) {
         override fun service(): BaseService<Department> {
        return departmentService
    }

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: Department): JsonResult<Department> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }

    @OperationLog("(web)获取科室/部门")
    @PostMapping("/getWebDepartmentList")
    @ApiOperation(value = "(web)获取科室/部门", notes = "(web)获取科室/部门")
    @ApiImplicitParam(name = "(web)获取科室/部门", value = "")
    fun getWebDepartmentList(@RequestBody params: MutableMap<String, String?>): JsonResult<Department> {
        params["status"] = "1"
        var list = departmentService.getListByParams(params)
        return jsonResult.successList(list,"查询成功！")
    }
}