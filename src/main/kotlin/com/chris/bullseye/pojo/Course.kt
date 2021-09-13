package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:27
 * 课程
 */
@Table(name = "kb_courses")
open class Course (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "所属机构")
    @Column(name = "organization_id")
    var organizationId: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "主讲人")
    @Column(name = "lecturer_id")
    var lecturerId: String? = null,

    @ApiModelProperty(name = "学时")
    @Column(name = "seconds")
    var seconds: Int? = null,

    @ApiModelProperty(name = "课程简介")
    @Column(name = "brief")
    var brief: String? = null,

    @ApiModelProperty(name = "课程分类")
    @Column(name = "category_id")
    var categoryId: String? = null,

    @ApiModelProperty(name = "0:不允许，1：个人用户：2：机构用户，3：所有人")
    @Column(name = "eval_range")
    var evalRange: Int? = null,

    @ApiModelProperty(name = "1：登录后，2：学习后，3：任何时候")
    @Column(name = "eval_scene")
    var evalScene: Int? = null,

    @ApiModelProperty(name = "是否评价")
    @Column(name = "eval_flag")
    var evalFlag: Boolean? = null,

    @ApiModelProperty(name = "是否允许多次评价")
    @Column(name = "eval_repeat_flag")
    var evalRepeatFlag: Boolean? = null,

    @ApiModelProperty(name = "课程价格")
    @Column(name = "price")
    var price: Int? = null,

    @ApiModelProperty(name = "详细介绍")
    @Column(name = "content")
    var content: String? = null,

    @ApiModelProperty(name = "是否推荐")
    @Column(name = "recommend_flag")
    var recommendFlag: Boolean? = null,

    @ApiModelProperty(name = "是否精品")
    @Column(name = "good_flag")
    var goodFlag: Boolean? = null,

    @ApiModelProperty(name = "是否免费")
    @Column(name = "free_flag")
    var freeFlag: Boolean? = null,

    @ApiModelProperty(name = "点击数")
    @Column(name = "click_num")
    var clickNum: Int? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "发布时间")
    @Column(name = "publish_date")
    var publishDate: Date? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @ApiModelProperty(name = "")
    @Column(name = "img")
    var img: String? = null,

    

)