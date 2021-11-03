package com.chris.bullseye.system.dto.response
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 */
 data class ConfigParameterResponse(

    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "播放设置、富文本设置、课程设置、安全设置")
    var dataId: String? = null,

    @ApiModelProperty(name = "编码")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    var name: String? = null,

    @ApiModelProperty(name = "单选、多选、文本输入")
    var displayType: Int? = null,

    @ApiModelProperty(name = "默认值")
    var defaultValue: String? = null,

    @ApiModelProperty(name = "设置值")
    var configValue: String? = null,

    @ApiModelProperty(name = "创建人ID")
    var creatorId: String? = null,

    @ApiModelProperty(name = "创建人")
    var creatorName: String? = null,

    @ApiModelProperty(name = "状态")
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建时间")
    var createTime: LocalDateTime? = null,
)