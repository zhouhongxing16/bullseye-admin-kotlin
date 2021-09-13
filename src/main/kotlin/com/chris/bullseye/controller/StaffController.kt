package com.chris.bullseye.controller

import com.chris.bullseye.dto.StaffDto
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.Major
import com.chris.bullseye.pojo.Navigation
import com.chris.bullseye.pojo.Staff
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.StaffService
import com.chris.bullseye.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Chris
 * @date 2020-12-24 9:28
 */

@Api(tags = ["员工"], produces = "员工")
@OperationLog("员工")
@RestController
@RequestMapping("/staff")
class StaffController(var staffService: StaffService,jsonResult: JsonResult<Staff>):BaseController<Staff>(jsonResult) {
    override fun service(): BaseService<Staff> {
        return staffService
    }


    @OperationLog("获取主讲人列表")
    @PostMapping("/getLectureList")
    @ApiOperation(value = "获取主讲人列表", notes = "获取主讲人列表")
    @ApiImplicitParam(name = "获取主讲人列表", value = "")
    fun getLectureList(@RequestBody params: MutableMap<String, String?>): JsonResult<Staff> {
        var result = JsonResult<Staff>()
        result.status = HttpStatus.OK.value()
        params["status"] = "1"
        var list = staffService.getListByParams(params)
        result.success = true
        result.list = list
        return result
    }

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: Staff): JsonResult<Staff> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }

    //增加
    @ApiOperation(value = "(admin)创建方法", notes = "创建")
    @ApiImplicitParam(name = "(admin)创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("(admin)创建方法")
    @PostMapping("/createDto")
    fun createDto(@RequestBody obj: StaffDto): JsonResult<StaffDto> {
        return staffService.createDto(obj)
    }
}