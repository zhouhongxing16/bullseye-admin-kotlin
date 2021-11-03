package com.chris.bullseye.system.controller

import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.AccountRole
import com.chris.bullseye.system.service.AccountRoleService
import com.chris.bullseye.system.service.RoleService
import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.system.dto.response.AccountResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * @author Chris
 * @date 2020-12-24 10:23
 */

@Api(tags = ["账号角色"], produces = "账号角色")
@OperationLog("账号角色")
@RestController
@RequestMapping("/accountrole")
class AccountRoleController(var accountRoleService: AccountRoleService,
                            var roleService: RoleService,
                            var jsonResult: JsonResult<AccountRole>) {

    @ApiOperation(value = "(admin)删除机构管理员", notes = "参数：accountId")
    @OperationLog("(admin)删除机构管理员")
    @GetMapping("/deleteOrganizationAdmin/{accountId}")
    fun deleteOrganizationAdmin(@PathVariable accountId: String): JsonResult<AccountResponse> {
        var result = JsonResult<AccountResponse>()
        var params: MutableMap<String, String?> = HashMap(2)
        params["roleCode"] = "organizationAdmin"
        params["accountId"] = accountId
        var status = accountRoleService.deleteAdminByAccountId(params)
        return if (status > 0) {
            result.success(null, "删除成功")
        } else {
            result.failed("删除失败")
        }
    }

    @ApiOperation(value = "(admin)添加机构管理员", notes = "参数：accountId")
    @OperationLog("(admin)添加机构管理员")
    @PostMapping("/addOrganizationAdmin/{organizationId}")
    fun addOrganizationAdmin(@RequestBody list: List<String>, @PathVariable organizationId: String): JsonResult<AccountRole> {
        var user = AuthUtil.getCurrentUser()
        var params: MutableMap<String, String?> = HashMap(2)
        params["roleCode"] = "organizationAdmin"
        var role = roleService.getByParams(params)
        if (role == null) {
            return jsonResult.failed("请先添加机构管理员角色")
        } else {
            params["roleId"] = role.id
            params["organizationId"] = organizationId
            //先清除掉该机构所有管理员角色
            accountRoleService.deleteByAccountRoleId(params)
            for (id in list) {
                var accountRole = AccountRole()
                accountRole.roleId = role.id
                accountRole.accountId = id
                accountRole.id = null
                accountRole.creatorId = user!!.id
                accountRole.creatorName = user!!.name
                accountRole.createTime = LocalDateTime.now()
                accountRoleService.add(accountRole)
            }
            return jsonResult.success(null, "添加成功！")
        }
    }

    @ApiOperation(value = "(admin)账号角色授权", notes = "参数：accountId")
    @OperationLog("(admin)账号角色授权")
    @PostMapping("/saveAccountRoles/{accountId}")
    fun saveAccountRoles(@RequestBody list: List<String>, @PathVariable accountId: String): JsonResult<Any> {
        return accountRoleService.saveAccountRoles(accountId, list)
    }

}