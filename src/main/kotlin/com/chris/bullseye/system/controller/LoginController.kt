package com.chris.bullseye.system.controller

import com.alibaba.fastjson.JSONObject
import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.IPUtils
import com.chris.bullseye.common.utils.RedisUtil
import com.chris.bullseye.common.utils.ValidateCodeUtils
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.entity.User
import com.chris.bullseye.system.entity.request.LoginRequest
import com.chris.bullseye.system.entity.response.LoginResponse
import com.chris.bullseye.system.pojo.LoginRecord
import com.chris.bullseye.system.pojo.Staff
import com.chris.bullseye.system.service.AccountService
import com.chris.bullseye.system.service.LoginRecordService
import com.chris.bullseye.system.service.RoleService
import com.chris.bullseye.system.service.StaffService
import eu.bitwalker.useragentutils.UserAgent
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Chris
 * @date 2021-09-15 9:49
 */


@RequestMapping("/login")
@RestController
@Api(value = "登录", tags = ["登录"])
@OperationLog("登录")
class LoginController(
        val accountService: AccountService,
        var staffService: StaffService,
        var loginRecordService: LoginRecordService,
        var roleService: RoleService,
        var authUtil: AuthUtil,
        var jsonResult: JsonResult<Any>
) {


    @ApiOperation(value = "管理员账号密码登录", notes = "参数：用户名username，密码password")
    @OperationLog("管理员登录")
    @PostMapping("/adminLogin")
    fun adminLogin(@RequestBody user: LoginRequest): JsonResult<Any> {
        return if (user.username.isNullOrEmpty() || user.password.isNullOrEmpty()) {
            JsonResult.failed("用戶名或密码不能为空！")
        } else {
            val accountDto = accountService.getAccountByUserName(user.username)
            login(accountDto, user.username, user.password, "admin")
        }
    }

    @ApiOperation(value = "(web)用户登录登录", notes = "(web)用户登录登录")
    @OperationLog("(web)用户登录登录")
    @PostMapping("/webLogin")
    fun webLogin(@RequestBody user: LoginRequest): JsonResult<Any> {

        return if (user.username.isNullOrEmpty() || user.password.isNullOrEmpty()) {
            JsonResult.failed("用戶名或密码不能为空！")
        } else {
            val accountDto = accountService.getAccountByUserName(user.username)
            login(accountDto, user.username, user.password, "web")
        }
    }

    @ApiOperation(value = "忘记密码", notes = "参数：密码password，手机号mobile,验证码validateCode")
    @PostMapping("/forgetPassword")
    fun forgetPassword(@RequestBody map: MutableMap<String, String?>): JsonResult<Any>? {
        return accountService.forgetPassword(map)
    }

    @ApiOperation(value = "管理员短信登录", notes = "参数：用户名username，密码password")
    @OperationLog("管理员短信登录")
    @PostMapping("/adminMobileLogin")
    fun adminMobileLogin(@RequestBody login: LoginRequest): JsonResult<Any> {
        val result = JsonResult<Any>()
        return if (login.code == ValidateCodeUtils.getRandomValidateCode(login.mobile)) {
            val accountDto = accountService.getAccountByStaffMobile(login.mobile)
            login(accountDto, accountDto?.username, "", "admin")
        } else {
            result.success = false
            result.message = "验证码错误"
            result
        }
    }



    fun login(accountDto: com.chris.bullseye.system.pojo.Account?, username: String?, password: String?, loginType: String?): JsonResult<Any> {
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
                var token = UUID.randomUUID().toString()
                var expireTime = LocalDateTime.now().plusHours(2)
                println("当前用户角色:$rolestr")
                val user = User()
                user.id =accountDto.id
                user.username =accountDto.username
                user.token =token
                user.organizationId =accountDto.organizationId
                user.departmentId =staff.departmentId
                user.staffId =staff.id
                user.accountLocked =accountDto.accountLocked
                user.accountExpired =accountDto.accountExpired
                user.authorities =grantedAuthorities
//                    User    (accountDto.id, accountDto.username, accountDto.password,token, accountDto.organizationId, staff.id, staff.departmentId, accountDto.accountLocked, accountDto.accountExpired,expireTime, grantedAuthorities)
                println(grantedAuthorities)
                //默认获取第一个角色为当前角色
                var currentRole = roles?.get(0)
                user.currentRole = currentRole
                user.loginType = loginType
                result.success = true
                result.message = "登录成功"
                result.status = HttpStatus.OK.value()
                var loginResponse = LoginResponse()
                loginResponse.token = UUID.randomUUID().toString()
                loginResponse.roleCoe = currentRole.code
                loginResponse.token = token
                result.data = loginResponse
                authUtil.setUserInfo(token,user)
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


    @RequestMapping(value = ["/unauth"])
    @Throws(IOException::class)
    fun unauth(request: HttpServletRequest, response: HttpServletResponse):JsonResult<Any> {
        request.characterEncoding = "UTF-8"
        response.characterEncoding = "UTF-8"
        val writer = response.writer
        println("unauth:授权认证失败，请重新登录")
        jsonResult.failed("授权认证失败，请重新登录！", HttpStatus.OK.value())
        writer.write(jsonResult.toString())
        writer.flush()
        writer.close()
       return   JsonResult.failed(HttpStatus.UNAUTHORIZED.name, HttpStatus.UNAUTHORIZED.value());
    }
}