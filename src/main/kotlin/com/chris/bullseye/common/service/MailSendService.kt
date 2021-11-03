package com.chris.bullseye.common.service

import com.chris.bullseye.common.entity.request.MailSendRequest
import com.chris.bullseye.common.utils.EmailTemplateUtil
import com.chris.bullseye.common.utils.Logger
import com.chris.bullseye.common.utils.ValidateCodeUtils
import com.chris.bullseye.system.dto.JsonResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.Resource

/**
 * @author Chris
 * @date 2021-10-08 14:07
 */
@Service
class MailSendService {


    @Value("\${spring.mail.username}")
    private val from: String? = null


    /**
     * spring 提供的邮件发送类
     */
    @Resource
    private val mailSender: JavaMailSender? = null



    //发送验证码邮件
    @Async("mailAsync")
    @Throws(Exception::class)
    fun sendCaptchaEmail(to: String?, subject: String?): JsonResult<Any>? {
        //创建一个MINE消息
        val message = mailSender!!.createMimeMessage()
        //true表示需要创建一个multipart message
        val helper = MimeMessageHelper(message, true)
        helper.setFrom(from!!)
        helper.setTo(to!!)
        helper.setSubject(subject!!)
        EmailTemplateUtil.getHTML(subject, ValidateCodeUtils.getRandomCode())?.let { helper.setText(it, true) }
        mailSender.send(message)
        Logger.info("html邮件发送成功")
        return JsonResult.success(null,"邮件发送成功！")
    }


    @Async("mailAsync")
    fun sendSimpleEmail(to: Array<String?>, subject: String?, content: String?) {
        //创建简单邮件消息
        val message = SimpleMailMessage()
        //设置发送人
        message.setFrom(from!!)
        /* //设置收件人
        message.setTo(to);*/

        //同时发送给多人
        message.setTo(*to)

        //设置主题
        message.setSubject(subject!!)
        //设置内容
        message.setText(content!!)
        try {
            //执行发送邮件
            mailSender!!.send(message)
            Logger.info("简单邮件已经发送。")
        } catch (e: Exception) {
            e.printStackTrace()
            Logger.error("发送简单邮件时发生异常！")
        }
    }

    @Async("mailAsync")
    @Throws(Exception::class)
    fun sendHtmlEmail(to: String?, subject: String?, content: String?) {
        //创建一个MINE消息
        val message = mailSender!!.createMimeMessage()

        //true表示需要创建一个multipart message
        val helper = MimeMessageHelper(message, true)
        helper.setFrom(from!!)
        helper.setTo(to!!)
        helper.setSubject(subject!!)
        helper.setText(content!!, true)
        mailSender.send(message)
        Logger.info("html邮件发送成功")
    }

    /**通用邮件发送方法
     */
    @Async("mailAsync")
    fun sendEmail(obj: MailSendRequest): Any? {
        val result = JsonResult<Any>()
        //创建一个MINE消息
        val message = mailSender!!.createMimeMessage()
        try {
            val helper = MimeMessageHelper(message, true)
            helper.setFrom(from!!)
            //设置收件人
            helper.setTo(obj.receiverList!!.toTypedArray())
            helper.setSubject(obj.subject!!)
            // true表示这个邮件是有附件的
            helper.setText(obj.content!!, true)

            result.success = true
            result.message = "邮件发送成功。"
            mailSender.send(message)
            Logger.info("邮件已经发送。")
        } catch (e: Exception) {
            e.printStackTrace()
            result.success = true
            result.message = "邮件发送失败。"
            Logger.error("发送邮件时发生异常！")
        }
        return result
    }

    @Async("mailAsync")
    fun sendInlineResourceEmail(to: String?, subject: String?, content: String?, rscPath: String?, rscId: String?) {
        val message = mailSender!!.createMimeMessage()
        try {
            val helper = MimeMessageHelper(message, true)
            helper.setFrom(from!!)
            helper.setTo(to!!)
            helper.setSubject(subject!!)
            helper.setText(content!!, true)
            val res = FileSystemResource(File(rscPath))

            //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
            //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
            helper.addInline(rscId!!, res)
            mailSender.send(message)
            Logger.info("嵌入静态资源的邮件已经发送。")
        } catch (e: Exception) {
            e.printStackTrace()
            Logger.error("发送嵌入静态资源的邮件时发生异常！")
        }
    }
}