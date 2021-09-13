package com.chris.bullseye.dto
import com.chris.bullseye.pojo.CourseCategory

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:26
 */
data class CourseCategoryDto(
     var userName: String? = null,

     var childNum: Int? = 0,

     var children:List<CourseCategory>? = null
) :CourseCategory() 