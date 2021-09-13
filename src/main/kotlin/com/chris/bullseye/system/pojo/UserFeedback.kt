package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:54
 * 用户反馈
 */
@Table(name = "b_user_feedback")
open class UserFeedback (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "用户标识")
    @Column(name = "user_id")
    var userId: String? = null,

    @ApiModelProperty(name = "联系人")
    @Column(name = "contact_name")
    var contactName: String? = null,

    @ApiModelProperty(name = "联系电话")
    @Column(name = "contact_phone")
    var contactPhone: String? = null,

    @ApiModelProperty(name = "反馈内容")
    @Column(name = "content")
    var content: String? = null,

    @ApiModelProperty(name = "附件")
    @Column(name = "attach")
    var attach: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    

)