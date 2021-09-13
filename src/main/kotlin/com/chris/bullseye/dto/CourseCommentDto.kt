package com.chris.bullseye.dto
import com.chris.bullseye.pojo.CourseComment
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 11:28
 */
data class CourseCommentDto(
     var userName: String? = null,

     var avatar: String? = null,

     var nickname: String? = null,

     var courseName: String? = null,
) :CourseComment()