package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:53
 * 员工分组
 */
@Table(name = "kb_staff_group")
open class StaffGroup (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "组织标识")
    @Column(name = "organization_id")
    var organizationId: String? = null,

    @ApiModelProperty(name = "代码")
    @Column(name = "code")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "人数")
    @Column(name = "nums")
    var nums: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    

)