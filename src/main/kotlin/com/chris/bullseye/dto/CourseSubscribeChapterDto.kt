package com.chris.bullseye.dto
import com.chris.bullseye.pojo.CourseSubscribeChapter
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2021-01-05 17:49
 */
data class CourseSubscribeChapterDto(
     var userName: String? = null,

     var videoId: String? = null,
) :CourseSubscribeChapter()