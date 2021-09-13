package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:24
 * 轮播
 */
@Table(name = "b_banner")
open class Banner (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "图片地址")
    @Column(name = "path")
    var path: String? = null,

    @ApiModelProperty(name = "跳转地址")
    @Column(name = "link")
    var link: String? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "userId")
    var userId: String? = null,

    @ApiModelProperty(name = "显示顺序")
    @Column(name = "sequence")
    var sequence: Int? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    

)