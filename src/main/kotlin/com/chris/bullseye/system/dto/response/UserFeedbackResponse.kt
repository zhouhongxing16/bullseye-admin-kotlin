package com.chris.bullseye.system.dto.response
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:54
 */
data class UserFeedbackResponse(

     @ApiModelProperty(name = "唯一标识")
     var id: String? = null,

     @ApiModelProperty(name = "用户标识")
     var creatorId: String? = null,

     @ApiModelProperty(name = "联系人")
     var contactName: String? = null,

     @ApiModelProperty(name = "联系电话")
     var contactPhone: String? = null,

     @ApiModelProperty(name = "反馈内容")
     var content: String? = null,

     @ApiModelProperty(name = "附件")
     var attach: String? = null,

     @ApiModelProperty(name = "状态")
     var status: Int? = null,

     @ApiModelProperty(name = "创建日期")
     var createTime: LocalDateTime? = null,
)