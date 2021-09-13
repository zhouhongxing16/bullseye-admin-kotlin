package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 18:07
 * 章节验证
 */
@Table(name = "kb_course_verify")
open class CourseVerify (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "章节标识")
    @Column(name = "chapter_id")
    var chapterId: String? = null,

    @ApiModelProperty(name = "开始验证位置（分钟）")
    @Column(name = "start_second")
    var startSecond: Int? = null,

    @ApiModelProperty(name = "观看验证题目")
    @Column(name = "verify_name")
    var verifyName: String? = null,

    @ApiModelProperty(name = "观看验证选项")
    @Column(name = "verify_options")
    var verifyOptions: String? = null,

    @ApiModelProperty(name = "观看验证答案")
    @Column(name = "verify_answer")
    var verifyAnswer: String? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    

)