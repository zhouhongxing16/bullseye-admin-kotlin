package com.chris.bullseye.entity.request

/**
 * @author Chris
 * @date 2021-01-21 13:57
 */
data class OrderRequest(

        var userId: String? = null,

        var leaveId: String? = null,

        var courseId:String?=null,

        var chapterId:String?=null,

        var orderId:String?=null,

        var payType:String?=null,
)
