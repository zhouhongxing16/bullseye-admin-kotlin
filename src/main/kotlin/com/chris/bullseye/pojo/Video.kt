package com.chris.bullseye.pojo

import java.util.Date
import javax.persistence.*
import java.io.Serializable
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 18:05
 * 视频
 */
@Table(name = "kb_videos")
open class Video (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "唯一标识")
    var id: String? = null,

    @ApiModelProperty(name = "名称")
    @Column(name = "name")
    var name: String? = null,

    @ApiModelProperty(name = "编号")
    @Column(name = "code")
    var code: String? = null,

    @ApiModelProperty(name = "总时长")
    @Column(name = "total_seconds")
    var totalSeconds: Int? = null,

    @ApiModelProperty(name = "视频简介")
    @Column(name = "brief")
    var brief: String? = null,

    @ApiModelProperty(name = "‘url'：直链，'aliyun'：阿里，'tencent':腾讯云")
    @Column(name = "source_type")
    var sourceType: String? = null,

    @ApiModelProperty(name = "视频源")
    @Column(name = "video_source")
    var videoSource: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "创建日期")
    @Column(name = "created")
    var created: Date? = null,

    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    var userId: String? = null,

    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    var status: Int? = null,

    

)