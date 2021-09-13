package com.chris.bullseye.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.pojo.UserFeedback
import org.apache.ibatis.annotations.Mapper
import java.util.Map

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:54
 */
@Mapper
interface UserFeedbackMapper: BaseMapper<UserFeedback> {
}