package com.chris.bullseye.system.controller

import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.Role
import com.chris.bullseye.system.service.BaseService
import com.chris.bullseye.system.service.RoleService
import com.chris.bullseye.common.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @author Chris
 * @date 2020-12-24 9:25
 */

@Api(tags = ["角色"], produces = "角色")
@OperationLog("角色")
@RestController
@RequestMapping("/role")
class RoleController(var roleService: RoleService){

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    fun create(@RequestBody obj: Role): JsonResult<Role> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return roleService.add(obj)
    }
    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/remove/{id}")
     fun remove(@PathVariable id: String): JsonResult<Any> {
        var role = roleService.getById(id)
        return if(role.code=="organizationAdmin" || role.code=="superAdmin" || role.code=="generalUser" || role.code=="courseAdmin"){
            JsonResult.failed("基础角色不能删除！")
        }else{
            var status = roleService.deleteById(id)
            if (status > 0) {
                JsonResult.success(null, "删除成功！")
            } else {
                JsonResult.failed("删除失败！")
            }
        }
    }

    @ApiOperation(value = "(admin)根据用户id获取角色", notes = "创建")
    @ApiImplicitParam(name = "(admin)根据用户id获取角色", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("(admin)创建方法")
    @GetMapping("/getRolesByAccountId/{accountId}")
    fun getRolesByAccountId(@PathVariable accountId: String?): JsonResult<Any> {
        val roles = roleService.getRolesByAccountId(accountId)
        return JsonResult.success(roles,"查询成功！")
    }
}