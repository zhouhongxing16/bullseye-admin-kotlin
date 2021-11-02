package com.chris.bullseye.system.controller

import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.system.dto.AccountDto
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.entity.request.AccountRequest
import com.chris.bullseye.system.entity.request.LoginRequest
import com.chris.bullseye.system.pojo.Account
import com.chris.bullseye.system.service.AccountService
import com.chris.bullseye.system.service.RoleService
import com.chris.bullseye.system.service.StaffService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.bind.annotation.*

/**
 * @author Chris
 * @date2020 12 02 15:49
 */
@RestController
@RequestMapping("/account")
@Api(value = "账号", tags = ["账号管理"])
@OperationLog("账号管理")
class AccountController(
        val accountService: AccountService,
        var staffService: StaffService,
        var roleService: RoleService,
        var jsonResult: JsonResult<Account>
) {


    @OperationLog("创建账号")
    @PostMapping("/create")
    @Throws(Exception::class)
    fun create(@RequestBody obj: Account): JsonResult<Account> {
        val user = AuthUtil.getCurrentUser()
        obj.organizationId = user?.organizationId
        val password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(obj.username)
        obj.password = password
        return accountService.add(obj)
    }



    @ApiOperation(value = "修改密码", notes = "参数：旧密码oldPassword，新密码newPassword")
    @OperationLog("查询分页数据")
    @PostMapping("/changePassword")
    fun changePassword(@RequestBody user: LoginRequest): JsonResult<Any>? {
        return accountService.changePassword(user)
    }

    @ApiOperation(value = "重置密码", notes = "参数：accountId")
    @OperationLog("重置密码")
    @GetMapping("/resetPassword/{accountId}")
    fun resetPassword(@PathVariable accountId: String): JsonResult<Any> {
        return accountService.resetPassword(accountId)
    }

   /* @ApiOperation(value = "(admin)获取机构管理员", notes = "参数：organizationId")
    @OperationLog("(admin)获取机构管理员")
    @PostMapping("/getOrganizationAdmins/{organizationId}")
    fun getOrganizationAdmins(@PathVariable organizationId: String): JsonResult<AccountDto> {
        var result = JsonResult<AccountDto>()
        var params: MutableMap<String, String?> = HashMap(2)
        params["roleCode"] = "organizationAdmin"
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        PageHelper.startPage<AccountDto>(params)
        val list = accountService.getDtoListByParams(params)
        var pageInfo = PageInfo(list)
        return result.success(pageInfo, "查询成功")
    }*/

   /* @ApiOperation(value = "(admin)获取机构非管理员用户和已授权用户", notes = "参数：organizationId")
    @OperationLog("(admin)获取机构非管理员用户和已授权用户")
    @PostMapping("/getNotAndAuthorizedUsers/{organizationId}")
    fun getNotAndAuthorizedUsers(@PathVariable organizationId: String): JsonResult<Any> {
        var result = JsonResult<AccountDto>()
        result.status = HttpStatus.OK.value()
        var params: MutableMap<String, String?> = HashMap(2)
        params["organizationId"] = organizationId
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        PageHelper.startPage<AccountDto>(params)
        val list = accountService.getNotInRoleCodeListByParams(params)
        var pageInfo = PageInfo(list)
        return JsonResultRsuccess(pageInfo, "查询成功！")
    }*/


    @ApiOperation(value = "获取我的个人信息", notes = "获取我的个人信息")
    @OperationLog("获取我的个人信息")
    @GetMapping("/getMyUserInfo")
    fun getMyUserInfo(): JsonResult<Any>? {
        var user = AuthUtil.getCurrentUser()
        var queryMap: MutableMap<String, String?> = HashMap(2)
        queryMap["staffId"] = user!!.staffId
        var staff = staffService.getByParams(queryMap)
        return JsonResult.success(staff, "")
    }



    @OperationLog("(admin)分页获取延展数据")
    @PostMapping("/getDtoListByPage")
    @ApiOperation(value = "(admin)分页获取延展数据", notes = "(admin)分页获取延展数据)分页获取延展数据")
    fun getDtoListByPage(@RequestBody account: AccountRequest): JsonResult<AccountDto> {
        var result = JsonResult<AccountDto>()

        var page = accountService.getDtoListByPage(account)
        return result.success(page, "查询成功")
    }


}
