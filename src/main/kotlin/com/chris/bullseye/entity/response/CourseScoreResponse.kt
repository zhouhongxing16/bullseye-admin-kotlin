package com.chris.bullseye.entity.response

import io.swagger.annotations.ApiModelProperty

/**
 * @author Chris
 * @date 2021-01-07 9:37
 */
data class CourseScoreResponse(

        @ApiModelProperty(name = "评分名称")
        var name: String? = null,


        @ApiModelProperty(name = "评分类型")
        var evalType: Int? = null,

        @ApiModelProperty(name = "评分人数")
        var num: Int? = null,

        )
