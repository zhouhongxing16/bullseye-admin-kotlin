package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.BaseMapper
import org.springframework.stereotype.Service
import com.chris.bullseye.system.pojo.UserFeedback
import com.chris.bullseye.system.mapper.UserFeedbackMapper

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-12-25 19:54
 */
@Service
class UserFeedbackService(var userFeedbackMapper: UserFeedbackMapper):BaseService<UserFeedback>() {

    override fun getMapper(): BaseMapper<UserFeedback> {
        return userFeedbackMapper
    }
}