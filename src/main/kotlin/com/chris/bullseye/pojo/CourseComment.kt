package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:28
 * 课程评论
 */
@Table(name = "kb_course_comment")
open class CourseComment (
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

    @ApiModelProperty(name = "评价得分")
    @Column(name = "score")
    var score: Int? = null,

    @ApiModelProperty(name = "评论信息")
    @Column(name = "comments")
    var comments: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @ApiModelProperty(name = "评价类型（0差评，1中评，2好评）")
    @Column(name = "eval_type")
    var evalType: Int? = null,

    

)