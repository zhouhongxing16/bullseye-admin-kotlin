package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:36
 * 专业
 */
@Table(name = "b_major")
open class Major (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "代码")
    @Column(name = "code")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "专业类型")
    @Column(name = "type_id")
    var typeId: String? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建时间")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "备注")
    @Column(name = "remark")
    var remark: String? = null,

    

)