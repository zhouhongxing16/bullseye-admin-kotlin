package com.chris.bullseye.system.dto.response
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:24
 */
data class BannerResponse(

     @ApiModelProperty(name = "唯一标识")
     var id: String? = null,

     @ApiModelProperty(name = "名称")
     var name: String? = null,

     @ApiModelProperty(name = "图片地址")
     var path: String? = null,

     @ApiModelProperty(name = "跳转地址")
     var link: String? = null,

     @ApiModelProperty(name = "显示顺序")
     var sort: Int? = null,

     @ApiModelProperty(name = "状态")
     var status: Int? = null,

     @ApiModelProperty(name = "创建人ID")
     var creatorId: String? = null,

     @ApiModelProperty(name = "创建人")
     var creatorName: String? = null,

     @ApiModelProperty(name = "创建日期")
     var createTime: LocalDateTime? = null

)