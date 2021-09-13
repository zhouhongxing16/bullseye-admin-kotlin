package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 17:43
 * 用户课程订阅
 */
@Table(name = "kb_course_subscribe")
open class CourseSubscribe (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "课程标识")
    @Column(name = "course_id")
    var courseId: String? = null,

    @ApiModelProperty(name = "用户标识")
    @Column(name = "user_id")
    var userId: String? = null,

    @ApiModelProperty(name = "学习状态")
    @Column(name = "status")
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "最后一次学习时间")
    @Column(name = "updated")
    var updated: Date? = null,

    @ApiModelProperty(name = "学习次数")
    @Column(name = "learn_times")
    var learnTimes: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "报名时间")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "是否已评论")
    @Column(name = "comment_flag")
    var commentFlag: Boolean? = null,

    

)