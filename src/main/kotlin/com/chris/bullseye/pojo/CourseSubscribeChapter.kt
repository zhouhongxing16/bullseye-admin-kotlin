package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 17:49
 * 用户章节订阅
 */
@Table(name = "kb_course_subscribe_chapter")
open class CourseSubscribeChapter (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "id")
    var id: String? = null,

    @ApiModelProperty(name = "课程订阅标识")
    @Column(name = "subscribe_id")
    var subscribeId: String? = null,

    @ApiModelProperty(name = "章节标识")
    @Column(name = "chapter_id")
    var chapterId: String? = null,

    @ApiModelProperty(name = "")
    @Column(name = "status")
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "订阅人")
    @Column(name = "user_id")
    var userId: String? = null,

    

)