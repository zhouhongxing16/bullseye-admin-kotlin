package com.chris.bullseye.controller

import com.alibaba.fastjson.JSONObject
import com.chris.bullseye.dto.AccountDto
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.entity.PageResult
import com.chris.bullseye.entity.User
import com.chris.bullseye.entity.response.LoginRequest
import com.chris.bullseye.pojo.Account
import com.chris.bullseye.pojo.LoginRecord
import com.chris.bullseye.pojo.Staff
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil
import com.chris.bullseye.utils.IPUtils
import com.chris.bullseye.utils.JwtHelper
import com.chris.bullseye.utils.ValidateCodeUtils
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import eu.bitwalker.useragentutils.UserAgent
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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
        var loginRecordService: LoginRecordService,
        var roleService: RoleService,
        jsonResult: JsonResult<Account>
) : BaseController<Account>(jsonResult) {


    override fun service(): BaseService<Account> = accountService


    @OperationLog("创建账号")
    @PostMapping("/create")
    @Throws(Exception::class)
    override fun create(@RequestBody obj: Account): JsonResult<Account> {
        val user = AuthUtil.getCurrentUser()
        obj.organizationId = user?.organizationId
        val password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(obj.username)
        obj.password = password
        return accountService.add(obj)
    }

    @ApiOperation(value = "管理员账号密码登录", notes = "参数：用户名username，密码password")
    @OperationLog("管理员登录")
    @PostMapping("/adminLogin")
    fun adminLogin(@RequestBody user:LoginRequest): JsonResult<Any> {
        return if (user.username.isNullOrEmpty() || user.password.isNullOrEmpty()) {
            JsonResult.failed("用戶名或密码不能为空！")
        } else {
            val accountDto = accountService.getAccountByUserName(user.username)
            login(accountDto, user.username, user.password,"admin")
        }
    }

    @ApiOperation(value = "(web)用户登录登录", notes = "(web)用户登录登录")
    @OperationLog("(web)用户登录登录")
    @PostMapping("/webLogin")
    fun webLogin(@RequestBody user:LoginRequest): JsonResult<Any> {
        return if (user.username.isNullOrEmpty() || user.password.isNullOrEmpty()) {
            JsonResult.failed("用戶名或密码不能为空！")
        } else {
            val accountDto = accountService.getAccountByUserName(user.username)
            login(accountDto, user.username, user.password,"web")
        }
    }


    @ApiOperation(value = "修改密码", notes = "参数：旧密码oldPassword，新密码newPassword")
    @OperationLog("查询分页数据")
    @PostMapping("/changePassword")
    fun changePassword(@RequestBody map: MutableMap<String, String?>): JsonResult<Any>? {
        return accountService.changePassword(map)
    }

    @ApiOperation(value = "重置密码", notes = "参数：accountId")
    @OperationLog("重置密码")
    @GetMapping("/resetPassword/{accountId}")
    fun resetPassword(@PathVariable  accountId:String): JsonResult<Any> {
        return accountService.resetPassword(accountId)
    }

    @ApiOperation(value = "(admin)获取机构管理员", notes = "参数：organizationId")
    @OperationLog("(admin)获取机构管理员")
    @PostMapping("/getOrganizationAdmins/{organizationId}")
    fun getOrganizationAdmins(@PathVariable  organizationId:String): JsonResult<AccountDto> {
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
        return result.successPageList(pageInfo.list, pageInfo.total, pageInfo.isIsLastPage, "查询成功")
    }

    @ApiOperation(value = "(admin)获取机构非管理员用户和已授权用户", notes = "参数：organizationId")
    @OperationLog("(admin)获取机构非管理员用户和已授权用户")
    @PostMapping("/getNotAndAuthorizedUsers/{organizationId}")
    fun getNotAndAuthorizedUsers(@PathVariable  organizationId:String): JsonResult<AccountDto> {
        var result = JsonResult<AccountDto>()
        result.status = HttpStatus.OK.value()
        var params: MutableMap<String, String?> = HashMap(2)
//        params["roleCode"] = "organizationAdmin"
        params["organizationId"] = organizationId
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        PageHelper.startPage<AccountDto>(params)
        val list = accountService.getNotInRoleCodeListByParams(params)
        params.clear()
        params["organizationId"] = organizationId
        params["roleCode"] = "organizationAdmin"
        val notInList = accountService.getDtoListByParams(params)
        var pageInfo = PageInfo(list)
        var page = PageResult<AccountDto>()
        page.total =  pageInfo.total
        page.list = pageInfo.list
        page.isLastPage =  pageInfo.isIsLastPage
        result.page = page
        result.success = true
        result.list=  notInList
        result.message ="查询成功！"
        return result
    }


    @ApiOperation(value = "获取我的个人信息", notes = "获取我的个人信息")
    @OperationLog("获取我的个人信息")
    @GetMapping("/getMyUserInfo")
    fun getMyUserInfo(): JsonResult<Any>? {
        var user = AuthUtil.getCurrentUser()
        var queryMap :MutableMap<String,String?> = HashMap(2)
        queryMap["staffId"]  = user!!.staffId
        var staff = staffService.getByParams(queryMap)
        return JsonResult.success(staff,"")
    }


    @ApiOperation(value = "忘记密码", notes = "参数：密码password，手机号mobile,验证码validateCode")
    @PostMapping("/forgetPassword")
    fun forgetPassword(@RequestBody map: MutableMap<String, String?>): JsonResult<Any>? {
        return accountService.forgetPassword(map)
    }

    @ApiOperation(value = "管理员短信登录", notes = "参数：用户名username，密码password")
    @OperationLog("管理员短信登录")
    @PostMapping("/adminMobileLogin")
    fun adminMobileLogin(@RequestBody map: MutableMap<String, String?>): JsonResult<Any> {
        val result = JsonResult<Any>()
        val mobile = map["mobile"]
        val code = map["code"]
        return if (code == ValidateCodeUtils.getRandomValidateCode(mobile)) {
            val accountDto = accountService.getAccountByStaffMobile(mobile)
            login(accountDto, accountDto?.username, "","admin")
        } else {
            result.success = false
            result.message = "验证码错误"
            result
        }
    }

    fun login(accountDto: Account?, username: String?, password: String?, loginType: String?): JsonResult<Any> {
        val result = JsonResult<Any>()
        if (accountDto != null && PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(password, accountDto.password)) {
            //判断账号过期
            if (accountDto.expiredDate != null && accountDto.accountExpired == true) {
                accountDto.accountExpired = Date() > accountDto.expiredDate
            } else {
                accountDto.accountExpired = false
            }
            val grantedAuthorities: MutableList<GrantedAuthority> = ArrayList()
            var staff = Staff()
            if (StringUtils.isNotEmpty(accountDto.staffId)) {
                staff = staffService.getById(accountDto.staffId)
            }
            //找到登录用户角色放到grantedAuthority中
            val roles = roleService.getRolesByAccountId(accountDto.id)
            var rolestr = ""
            if (!roles.isNullOrEmpty()) {
                for (role in roles) {
                    if (role != null && role.code.isNullOrEmpty()) {
                        rolestr = rolestr + "," + role.code
                        val grantedAuthority: GrantedAuthority = SimpleGrantedAuthority(role.code)
                        //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                        grantedAuthorities.add(grantedAuthority)
                    }
                }
                println("当前用户角色:$rolestr")
                val user = User(accountDto.id, accountDto.username, accountDto.password, accountDto.organizationId, staff.id, staff.departmentId, accountDto.accountLocked, accountDto.accountExpired, grantedAuthorities)
                println(grantedAuthorities)
                //默认获取第一个角色为当前角色
                user.currentRole = roles?.get(0)
                user.loginType = loginType
                result.success = true
                result.message = "登录成功"
                result.status = HttpStatus.OK.value()
                result.data = JwtHelper.createJWT(JSONObject.toJSONString(user))
            } else {
                result.success = false
                result.message = "当前账号无角色，请先配置角色！"
            }

        } else {
            result.success = false
            result.message = "用户名或密码错误！"
        }

        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
        val userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"))
        // 获取客户端操作系统
        val os: String = userAgent.operatingSystem.getName()
        // 获取客户端浏览器
        val browser: String = userAgent.browser.getName()
        Thread {
            val ip = IPUtils.getIpAddr(request)
            val loginRecord = LoginRecord()
            loginRecord.id = null
            loginRecord.username = username
            loginRecord.ip = ip
            loginRecord.os = os
            loginRecord.browser = browser
            loginRecord.loginLocation = ""
            loginRecord.status = if (result.success) 1 else 0
            loginRecord.message = (if (result.success) "登录成功" else "用户名或密码错误！")
            loginRecordService.add(loginRecord)
        }.start()
        return result
    }


/*

    @OperationLog("(admin)分页获取延展数据")
    @PostMapping("/getDtoListByPage")
    @ApiOperation(value = "(admin)分页获取延展数据", notes = "(admin)分页获取延展数据)分页获取延展数据")
    @ApiImplicitParam(name = "(admin)分页获取延展数据", value = "")
    fun getDtoListByPage(@RequestBody params: MutableMap<String, String?>): JsonResult<AccountDto> {
        var result = JsonResult<AccountDto>()
        if (params["pageNum"].isNullOrEmpty()) {
            params["pageNum"] = "1"
        }
        if (params["pageSize"].isNullOrEmpty()) {
            params["pageSize"] = "10"
        }
        PageHelper.startPage<AccountDto>(params)
        var list = accountService.getDtoListByParams(params)
        var pageInfo = PageInfo(list)
        return result.successPageList(pageInfo.list, pageInfo.total, pageInfo.isIsLastPage, "查询成功")
    }
*/


    @RequestMapping(value = ["/unauth"])
    @Throws(IOException::class)
    fun unauth(request: HttpServletRequest, response: HttpServletResponse) {

//        response.status = HttpStatus.UNAUTHORIZED.value()
        request.characterEncoding = "UTF-8"
        response.characterEncoding = "UTF-8"
        val writer = response.writer
        println("unauth:授权认证失败，请重新登录")
        jsonResult.failed("授权认证失败，请重新登录！",HttpStatus.OK.value())
        writer.write(jsonResult.toString())
        writer.flush()
        writer.close()
//       return  new JsonResult<>(false,null,"", HttpStatus.UNAUTHORIZED.name(),HttpStatus.UNAUTHORIZED.value());
    }
}
