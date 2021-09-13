package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-04 16:49
 * 首页栏目
 */
@Table(name = "kb_index_block")
open class IndexBlock (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "标题")
    @Column(name = "title")
    var title: String? = null,

    @ApiModelProperty(name = "图标")
    @Column(name = "icon")
    var icon: String? = null,

    @ApiModelProperty(name = "0:课程分类，1：指定课程")
    @Column(name = "type_flag")
    var typeFlag: Int? = null,

    @ApiModelProperty(name = "课程分类标识")
    @Column(name = "course_category_id")
    var courseCategoryId: String? = null,

    @ApiModelProperty(name = "显示条数")
    @Column(name = "display_num")
    var displayNum: Int? = null,

    @ApiModelProperty(name = "是否显示更多")
    @Column(name = "more_flag")
    var moreFlag: Boolean? = null,

    @ApiModelProperty(name = "1:块状，2：列表")
    @Column(name = "display_style")
    var displayStyle: String? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @ApiModelProperty(name = "排序")
    @Column(name = "sequence")
    var sequence: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    

)