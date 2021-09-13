package com.chris.bullseye.entity.response

import io.swagger.annotations.ApiModelProperty

/**
 * @author Chris
 * @date 2021-01-06 17:04
 */
data class StaffResponse(

        @ApiModelProperty(name = "姓名")
        var name: String? = null,

        @ApiModelProperty(name = "专业")
        var majorName: String? = null,

        @ApiModelProperty(name = "照片")
        var img: String? = null,

        @ApiModelProperty(name = "id")
        var id: String? = null,

        @ApiModelProperty(name = "组织")
        var organizationName: String? = null,

        @ApiModelProperty(name = "职称")
        var titleName : String? = null,

)
