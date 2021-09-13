package com.chris.bullseye.utils

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * @author Chris
 * @date 2020 12 07 17:50
 */
class ValidateCodeUtils {

    companion object{

        // 验证码图片的宽度。
        private val width = 60

        // 验证码图片的高度。
        private val height = 20

        // 验证码字符个数
        private val codeCount = 4

        /*
         * char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
         * 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
         * 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
         * 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
         * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
         */
        private val codeSequence = charArrayOf('0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9')

        /**
         * 获取系统验证码图片
         * @return
         */
        fun getValidateCodeImage(session: HttpSession): BufferedImage? {
            val x = width / (codeCount + 1)
            val fontHeight = height // 字体高度
            val codeY = height

            // 定义图像buffer
            val buffImg = BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB)
            val g = buffImg.createGraphics()
            // 创建一个随机数生成器类
            val random = Random()
            // 将图像填充为白色
            g.color = getRandColor(220, 250)
            g.fillRect(0, 0, width, height)
            // 创建字体，字体的大小应该根据图片的高度来定。
            val font = Font("黑体", Font.BOLD, fontHeight - 5)
            // 设置字体。
            g.font = font
            // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
            val randomCode = StringBuffer()
            // 随机产生codeCount数字的验证码。
            for (i in 0 until codeCount) {
                // 得到随机产生的验证码数字。
                val strRand = codeSequence[random.nextInt(10)].toString()
                // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
                g.color = getRandColor(20, 130)
                // 用随机产生的颜色将验证码绘制到图像中。
                g.drawString(strRand, (i + 1) * x - 7, codeY - 5)
                // 将产生的四个随机数组合在一起。
                randomCode.append(strRand)
            }

            // 将验证码写入Session
            session.setAttribute("VALIDATE_CODE", randomCode.toString())
            return buffImg
        }


        /**
         * 获取验证码字符串
         * @return
         */
        fun getRandomValidateCode(session: HttpSession): String? {
            val code = getRandomCode()
            // 将验证码写入Session/缓存
            session.setAttribute("VALIDATE_CODE", code)
            return code
        }

        /**
         * 获取验证码字符串
         * @return
         */
        fun getRandomCode(): String {
            // 创建一个随机数生成器类
            val random = Random()
            // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
            val randomCode = StringBuffer()
            // 随机产生codeCount数字的验证码。
            for (i in 0 until codeCount) {
                // 得到随机产生的验证码数字。
                val strRand = codeSequence[random.nextInt(10)].toString()
                // 将产生的四个随机数组合在一起。
                randomCode.append(strRand)
            }
            return randomCode.toString()
        }


        /**
         * 获取验证码字符串（用于验证用户验证码输入）
         * @return
         */
        fun getValidateCode(): String? {
            val request: HttpServletRequest = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
            val code = request.session.getAttribute("VALIDATE_CODE") as String
            Logger.debug("验证码是：$code")
            return code
        }

        /* *
          * 获取验证码字符串（用于验证用户验证码输入）
          * @return
          */
        fun getValidateCode(mobile: String?): String? {
            val code = EhcacheUtil.get(mobile)
            Logger.debug("验证码是：$code")
            return code?.toString() ?: ""
        }

        /* *
          * 获取验证码字符串
          * @return
          * */

        fun getRandomValidateCode(mobile: String?): String? {
            val code = getRandomCode()
            // 将验证码写入Session/缓存
            EhcacheUtil.put(mobile, code)
            return code
        }

        /**
         * 获取验证码字符串（用于验证用户验证码输入）
         * @return
         */
        fun removeValidateCode() {
            val request: HttpServletRequest = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
            request.session.removeAttribute("VALIDATE_CODE")
        }

        /**
         * 产生随机颜色
         *
         * @param num1
         * @param num2
         * @return Color
         */
        private fun getRandColor(num1: Int, num2: Int): Color? {
            var num1 = num1
            var num2 = num2
            val random = Random()
            if (num1 > 255) num1 = 255
            if (num2 > 255) num2 = 255
            val r = num1 + random.nextInt(num2 - num1)
            val g = num1 + random.nextInt(num2 - num1)
            val b = num1 + random.nextInt(num2 - num1)
            return Color(r, g, b)
        }
    }

}