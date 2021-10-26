package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.mapper.LoginRecordMapper
import com.chris.bullseye.system.pojo.LoginRecord
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020 12 07 17:02
 */

@Service
class LoginRecordService(var loginRecordMapper: LoginRecordMapper):BaseService<LoginRecord>() {
    override fun getMapper(): MPBaseMapper<LoginRecord> {
        return loginRecordMapper
    }

}