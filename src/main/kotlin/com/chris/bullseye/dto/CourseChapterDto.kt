package com.chris.bullseye.dto
import com.chris.bullseye.pojo.CourseChapter
import com.chris.bullseye.pojo.Video

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-28 18:06
 */
data class CourseChapterDto(
     var userName: String? = null,

     var playUrl: String? = null,

     var childNum: Int? = 0,

     var studyStatus: Int? = 0,

     var children:List<CourseChapter>? = null,

     var video: Video?=null,

     var verifyList: List<CourseVerifyDto>?=null,
) :CourseChapter() 