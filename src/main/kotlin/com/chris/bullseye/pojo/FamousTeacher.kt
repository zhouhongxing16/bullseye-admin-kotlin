package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 18:53
 * 名师
 */
@Table(name = "kb_famous_teacher")
open class FamousTeacher (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "机构标识")
    @Column(name = "organization_id")
    var organizationId: String? = null,

    @ApiModelProperty(name = "人员标识")
    @Column(name = "staff_id")
    var staffId: String? = null,

    @ApiModelProperty(name = "师资简介")
    @Column(name = "brief")
    var brief: String? = null,

    @ApiModelProperty(name = "详细介绍")
    @Column(name = "content")
    var content: String? = null,

    @ApiModelProperty(name = "专长")
    @Column(name = "characters")
    var characters: String? = null,

    @ApiModelProperty(name = "流程标识")
    @Column(name = "process_instance_id")
    var processInstanceId: String? = null,

    @ApiModelProperty(name = "课程数量")
    @Column(name = "course_num")
    var courseNum: Int? = null,

    @ApiModelProperty(name = "荣誉勋章")
    @Column(name = "honor_level")
    var honorLevel: Int? = null,

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

    

)