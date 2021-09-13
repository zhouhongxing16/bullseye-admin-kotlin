package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.LoginRecordMapper
import com.chris.bullseye.pojo.LoginRecord
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020 12 07 17:02
 */

@Service
class LoginRecordService(var loginRecordMapper: LoginRecordMapper):BaseService<LoginRecord>() {
    override fun getMapper(): BaseMapper<LoginRecord> {
        return loginRecordMapper
    }

}