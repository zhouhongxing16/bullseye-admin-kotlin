package com.chris.bullseye.controller

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.chris.bullseye.entity.Constants
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.utils.AuthUtil
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * @author Chris
 *
 *
 * @date2020-12-01 14:46
 */
abstract class BaseController<T>(var jsonResult: JsonResult<T>) {
    abstract fun service(): BaseService<T>

    @ApiOperation(value = "默认分页查询", notes = "根据传递的参数进行查询")
    @ApiImplicitParam(name = "分页查询", value = "参数:{pageNum:number,pageSize:number,name:string}")
    @OperationLog("查询分页数据")
    @PostMapping("/listByPage")
    open fun listPage(@RequestBody params: MutableMap<String, String?>): JsonResult<T> {
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        PageHelper.startPage<T>(params)
        val list = service().getListByParams(params)
        var pageInfo = PageInfo(list)

        return jsonResult.successPageList(pageInfo.list, pageInfo.total, pageInfo.isIsLastPage, "查询成功")

    }

    @ApiOperation(value = "默认分页查询(带数据权限)", notes = "根据传递的参数进行查询")
    @ApiImplicitParam(name = "分页查询(带数据权限)", value = "参数:{pageNum:number,pageSize:number,name:string}")
    @OperationLog("查询分页数据(带数据权限)")
    @PostMapping("/listAuthByPage")
    open fun listAuthByPage(@RequestBody params: MutableMap<String, String?>): JsonResult<T> {
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        PageHelper.startPage<T>(params)
        val list = service().getListByParams(params)
        var pageInfo = PageInfo(list)
        return jsonResult.successPageList(pageInfo.list, pageInfo.total, pageInfo.isIsLastPage, "查询成功")
    }

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    @Throws(Exception::class)
    open fun create(@RequestBody obj: T): JsonResult<T> {
        return service().add(obj)
    }

    //更新
    @ApiOperation(value = "更新一条数据", notes = "更新一条数据")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("更新")
    @PostMapping("/update")
    open fun update(@RequestBody obj: T): JsonResult<T> {
        var status = service().update(obj)
        return if (status > 0) {
            jsonResult.success(obj, "修改成功！")
        } else {
            jsonResult.failed("修改失败！")
        }
    }

    //删除
    @ApiOperation(value = "根据ID删除一条数据", notes = "根据ID删除一条数据")
    @GetMapping("/delete/{id}")
    @OperationLog("删除")
    open fun remove(@PathVariable id: String): JsonResult<T> {
        return try {
            var status = service().deleteById(id)
            if (status > 0) {
                jsonResult.success(null, "删除成功！")
            } else {
                jsonResult.failed("删除失败！")
            }
        }catch (e:Exception){
            jsonResult.failed("删除失败！")
        }

    }

    //获取一条数据
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据ID获取一条数据", notes = "根据ID获取一条数据")
    @OperationLog("获取一条数据")
    open fun getById(@PathVariable id: String): JsonResult<T> {
        var obj = service().getById(id)
        return jsonResult.success(obj, "获取成功！")
    }


    //获取一个list
    @PostMapping("/getListByParams")
    @ApiOperation(value = "获取列表数据", notes = "获取列表数据")
    @ApiImplicitParam(name = "params", value = "参数：任意参数")
    @OperationLog("获取列表数据")
    open fun getListByParams(@RequestBody params: MutableMap<String, String?>): JsonResult<T> {
        val list: List<T> = service().getListByParams(params)
        return jsonResult.successList(list, null)
    }

    //获取一个list
    @PostMapping("/getAuthListByParams")
    @ApiOperation(value = "获取列表数据(带数据权限)", notes = "获取列表数据(带数据权限)")
    @ApiImplicitParam(name = "params", value = "参数：任意参数")
    @OperationLog("获取列表数据(带数据权限)")
    open fun getAuthListByParams(@RequestBody params: MutableMap<String, String?>): JsonResult<T> {
        params.putAll(getAuthParameterMap(params))
        val list: List<T> = service().getListByParams(params)
        return jsonResult.successList(list, null)
    }

    protected fun getAuthParameterMap(map: MutableMap<String, String?>): MutableMap<String, String?> {
        val user = AuthUtil.getCurrentUser()
        val currentRole = user?.currentRole
        val authFlag = currentRole?.dataAuthFlag
        map[Constants.ORGANIZATION_ID] = user?.organizationId
        if (currentRole != null) {
            if (Constants.ORGANIZATION == authFlag) {
                map[Constants.ORGANIZATION_ID] = user.organizationId
            } else if (Constants.DEPARTMENT == authFlag) {
                map[Constants.DEPARTMENT_ID] = user.departmentId
            } else if (Constants.PERSONAL == authFlag) {
                map[Constants.DEPARTMENT_ID] = user.departmentId
                map[Constants.STAFF_ID] = user.staffId
            }
        }
        return map
    }
}
