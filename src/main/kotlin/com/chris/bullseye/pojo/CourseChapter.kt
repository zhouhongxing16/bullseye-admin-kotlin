package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 18:06
 * 课程章节
 */
@Table(name = "kb_course_chapter")
open class CourseChapter (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "父标识")
    @Column(name = "parent_id")
    var parentId: String? = null,

    @ApiModelProperty(name = "课程标识")
    @Column(name = "course_id")
    var courseId: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "价格")
    @Column(name = "price")
    var price: Int? = null,

    @ApiModelProperty(name = "简介")
    @Column(name = "brief")
    var brief: String? = null,

    @ApiModelProperty(name = "视频ID")
    @Column(name = "video_id")
    var videoId: String? = null,

    @ApiModelProperty(name = "显示顺序")
    @Column(name = "sequence")
    var sequence: Int? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @ApiModelProperty(name = "是否启用观看验证")
    @Column(name = "verify_flag")
    var verifyFlag: Boolean? = null,

    @ApiModelProperty(name = "是否允许购买")
    @Column(name = "buy_flag")
    var buyFlag: Boolean? = null,

    @ApiModelProperty(name = "try_flag")
    @Column(name = "try_flag")
    var tryFlag: Boolean? = null,

    @ApiModelProperty(name = "试看长度")
    @Column(name = "try_seconds")
    var trySeconds: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    

)