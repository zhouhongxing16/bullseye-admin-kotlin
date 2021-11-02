package com.chris.bullseye.common.controller

import com.chris.bullseye.common.entity.request.MailSendRequest
import com.chris.bullseye.common.service.MailSendService
import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @author Chris
 * @date 2021-10-08 14:12
 */
@Api(tags = ["mail"], description = "邮件发送")
@OperationLog("邮件发送")
@RestController
@RequestMapping("/mail")
class SendMailController(var mailSendService: MailSendService) {


    /**
     * 通用邮件发送接口
     *
     * @param
     * @return
     */
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @PostMapping(value = ["/sendMail"])
    @ResponseBody
    @OperationLog("发送邮件")
    fun sendMail(@RequestBody obj: MailSendRequest): Any? {
        val result = JsonResult<Any>()
        return if (obj.reciverList.isNullOrEmpty()) {
            result.success = false
            result.message = "接收人不能为空！"
            result
        } else if (obj.subject.isNullOrEmpty()) {
            result.success = false
            result.message="主题不能为空！"
            result
        } else if (obj.content.isNullOrEmpty()) {
            result.success = false
            result.message="邮件内容不能为空！"
            result
        } else {
            mailSendService.sendEmail(obj)
        }
    }
}