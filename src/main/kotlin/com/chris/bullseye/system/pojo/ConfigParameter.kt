package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:25
 * 系统设置
 */
@Table(name = "b_config_parameter")
open class ConfigParameter (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "播放设置、富文本设置、课程设置、安全设置")
    @Column(name = "data_id")
    var dataId: String? = null,

    @ApiModelProperty(name = "编码")
    @Column(name = "code")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "单选、多选、文本输入")
    @Column(name = "display_type")
    var displayType: Int? = null,

    @ApiModelProperty(name = "默认值")
    @Column(name = "default_value")
    var defaultValue: String? = null,

    @ApiModelProperty(name = "设置值")
    @Column(name = "config_value")
    var configValue: String? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建时间")
    @Column(name = "created")
    var created: Date? = null,

    

)