package com.chris.bullseye.system.pojo

import java.util.Date
import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:13
 * 日志
 */
@Table(name = "b_logs")
open class Logs(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "ID")
        var id: String? = null,

        @ApiModelProperty(name = "所属组织")
        @Column(name = "organization_id")
        var organizationId: String? = null,

        @ApiModelProperty(name = "操作类")
        @Column(name = "option_name")
        var optionName: String? = null,

        @ApiModelProperty(name = "操作方法名")
        @Column(name = "option_type")
        var optionType: String? = null,

        @ApiModelProperty(name = "操作方法")
        @Column(name = "method")
        var method: String? = null,

        @ApiModelProperty(name = "参数")
        @Column(name = "params")
        var params: String? = null,

        @ApiModelProperty(name = "操作人")
        @Column(name = "user_id")
        var userId: String? = null,

        @ApiModelProperty(name = "IP")
        @Column(name = "ip")
        var ip: String? = null,

        @ApiModelProperty(name = "执行时长")
        @Column(name = "execution_time")
        var executionTime: Int? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "备注")
        @Column(name = "remark")
        var remark: String? = null,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @ApiModelProperty(name = "操作时间")
        @Column(name = "created")
        var created: Date? = null,


        )