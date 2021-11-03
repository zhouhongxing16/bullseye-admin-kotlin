package com.chris.bullseye.system.dto.response
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:25
 */
data class NavigationResponse(
     @ApiModelProperty(name = "唯一标识")
     var id: String? = null,

     @ApiModelProperty(name = "名称")
     var name: String? = null,

     @ApiModelProperty(name = "级别")
     var level: Int? = null,

     @ApiModelProperty(name = "父标识")
     var parentId: String? = null,

     @ApiModelProperty(name = "显示顺序")
     var sort: Int? = null,

     @ApiModelProperty(name = "访问路径")
     var path: String? = null,

     @ApiModelProperty(name = "状态")
     var status: Int? = null,

     @ApiModelProperty(name = "创建日期")
     var created: LocalDateTime? = null,

     @ApiModelProperty(name = "创建人ID")
     var creatorId: String? = null,

     @ApiModelProperty(name = "创建人")
     var creatorName: String? = null,

     @ApiModelProperty(name = "创建日期")
     var createTime: LocalDateTime? = null,
)