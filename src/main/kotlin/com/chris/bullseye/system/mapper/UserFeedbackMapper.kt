package com.chris.bullseye.system.mapper

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.system.pojo.UserFeedback
import org.apache.ibatis.annotations.Mapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:54
 */
@Mapper
interface UserFeedbackMapper: BaseMapper<UserFeedback> {
}