package com.chris.bullseye.system.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 16:25
 * 字典数据
 */
@Table(name = "b_dictionary_data")
open class DictionaryData(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
        @ApiModelProperty(name = "唯一标识")
        var id: String? = null,

        @ApiModelProperty(name = "类型标识")
        @Column(name = "type_id")
        var typeId: String? = null,

        @ApiModelProperty(name = "字典编码")
        @Column(name = "code")
        var code: String? = null,

        @ApiModelProperty(name = "字典名称")
        @Column(name = "name")
        var name: String? = null,

        @ApiModelProperty(name = "字典备注")
        @Column(name = "remark")
        var remark: String? = null,

        @ApiModelProperty(name = "排序")
        @Column(name = "sort")
        var sort: Int? = null,

        @ApiModelProperty(name = "状态")
        @Column(name = "status")
        var status: Int? = null,

        @ApiModelProperty(name = "创建人ID")
        @Column(name = "creator_id")
        var creatorId: String? = null,

        @ApiModelProperty(name = "创建人")
        @Column(name = "creator_name")
        var creatorName: String? = null,

        @ApiModelProperty(name = "创建日期")
        @Column(name = "create_time")
        var createTime: LocalDateTime? = null,


        )