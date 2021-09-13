package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:26
 * 课程分类
 */
@Table(name = "kb_course_category")
open class CourseCategory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "父标识")
    @Column(name = "parent_id")
    var parentId: String? = null,

    @ApiModelProperty(name = "代码")
    @Column(name = "code")
    var code: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "专业标识")
    @Column(name = "major_id")
    var majorId: String? = null,

    @ApiModelProperty(name = "科室标识")
    @Column(name = "section_id")
    var sectionId: String? = null,

    @ApiModelProperty(name = "层级")
    @Column(name = "level")
    var level: Int? = null,

    @ApiModelProperty(name = "显示顺序")
    @Column(name = "sequence")
    var sequence: Int? = null,

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