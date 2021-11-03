package com.chris.bullseye.common.entity.request

/**
 * @author Chris
 * @date 2021-10-08 14:21
 */
data class MailSendRequest(
        var subject:String? = null,

        var content:String? = null,

        var filePath:String? = null,

        var receiverList:List<String>? = null,
)
