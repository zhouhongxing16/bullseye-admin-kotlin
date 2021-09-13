package com.chris.bullseye.controller

import com.chris.bullseye.pojo.LoginRecord
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil

import com.chris.bullseye.service.LoginRecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:58
 */
@Api(value = "登录日志",tags = ["登录日志"])
@OperationLog("登录日志")
@RestController
@RequestMapping("/loginrecord")
class LoginRecordController(
        val loginRecordService: LoginRecordService,
        jsonResult: JsonResult<LoginRecord>
) : BaseController<LoginRecord>(jsonResult) {
         override fun service(): BaseService<LoginRecord> {
        return loginRecordService
    }
}