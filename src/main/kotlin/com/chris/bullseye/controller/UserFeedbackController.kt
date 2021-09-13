package com.chris.bullseye.controller

import com.chris.bullseye.pojo.UserFeedback
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.StaffGroup
import com.chris.bullseye.service.*
import com.chris.bullseye.utils.AuthUtil

import com.chris.bullseye.service.UserFeedbackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:54
 */
@Api(value = "用户反馈",tags = ["用户反馈"])
@OperationLog("用户反馈")
@RestController
@RequestMapping("/userfeedback")
class UserFeedbackController(
        val userFeedbackService: UserFeedbackService,
        jsonResult: JsonResult<UserFeedback>
) : BaseController<UserFeedback>(jsonResult) {
         override fun service(): BaseService<UserFeedback> {
        return userFeedbackService
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: UserFeedback): JsonResult<UserFeedback> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }
}