package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:25
 * 导航栏
 */
@Table(name = "kb_navigations")
open class Navigation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "级别")
    @Column(name = "level")
    var level: Int? = null,

    @ApiModelProperty(name = "父标识")
    @Column(name = "parent_id")
    var parentId: String? = null,

    @ApiModelProperty(name = "显示顺序")
    @Column(name = "sequence")
    var sequence: Int? = null,

    @ApiModelProperty(name = "访问路径")
    @Column(name = "path")
    var path: String? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    

)