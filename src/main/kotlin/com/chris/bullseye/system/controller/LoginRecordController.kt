package com.chris.bullseye.system.controller

import com.chris.bullseye.system.pojo.LoginRecord
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.service.*

import com.chris.bullseye.system.service.LoginRecordService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.annotations.Api

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
) {
}