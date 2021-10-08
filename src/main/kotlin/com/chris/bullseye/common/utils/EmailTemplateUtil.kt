package com.chris.bullseye.common.utils

/**
 * @author Chris
 * @date 2021-10-08 15:19
 */
class EmailTemplateUtil {
    companion object {

        fun getHTML(type: String, captcha: String): String? {
            val url = this.javaClass.classLoader.getResource("templates/emailTemplate.html")
            var template = FileUtil.toHtmlString(url.path)
            if(!template.isNullOrEmpty()){
                template = template.replace("$(type)", type)
                template = template.replace("$(captcha)", captcha)
            }
            return template
        }

    }

}