package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:38
 * 菜单
 */
@Table(name = "kb_menus")
open class Menu(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "父菜单id")
        @Column(name = "parent_id")
        var parentId: String? = null,

        @ApiModelProperty(name = "代码")
        @Column(name = "code")
        var code: String? = null,

        @ApiModelProperty(name = "名称")
        @Column(name = "title")
        var title: String? = null,

        @ApiModelProperty(name = "图标")
        @Column(name = "icon")
        var icon: String? = null,

        @ApiModelProperty(name = "路径")
        @Column(name = "path")
        var path: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "user_id")
        var userId: String? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "显示顺序")
        @Column(name = "sequence")
        var sequence: Int? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "               ")
        @Column(name = "created")
        var created: Date? = null,


        )