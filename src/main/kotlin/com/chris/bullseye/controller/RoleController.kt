package com.chris.bullseye.controller

import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.Role
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.RoleService
import com.chris.bullseye.utils.AuthUtil
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
class RoleController(var roleService: RoleService,jsonResult: JsonResult<Role>):BaseController<Role>(jsonResult) {
    override fun service(): BaseService<Role> {
        return roleService
    }

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: Role): JsonResult<Role> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }

    override fun remove(id: String): JsonResult<Role> {
        var role = roleService.getById(id)
        return if(role.code=="organizationAdmin" || role.code=="superAdmin" || role.code=="generalUser" || role.code=="courseAdmin"){
            jsonResult.failed("基础角色不能删除！")
        }else{
            var status = service().deleteById(id)
            if (status > 0) {
                jsonResult.success(null, "删除成功！")
            } else {
                jsonResult.failed("删除失败！")
            }
        }
    }

    @ApiOperation(value = "(admin)根据用户id获取角色", notes = "创建")
    @ApiImplicitParam(name = "(admin)根据用户id获取角色", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("(admin)创建方法")
    @GetMapping("/getRolesByAccountId/{accountId}")
    fun getRolesByAccountId(@PathVariable accountId: String?): JsonResult<Role> {
        val roles = roleService.getRolesByAccountId(accountId)
        return jsonResult.successList(roles,"查询成功！")
    }
}