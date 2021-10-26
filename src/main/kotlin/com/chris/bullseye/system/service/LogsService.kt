package com.chris.bullseye.system.service

import com.chris.bullseye.basemapper.MPBaseMapper
import com.chris.bullseye.system.mapper.LogsMapper
import com.chris.bullseye.system.pojo.Logs
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020 12 07 17:08
 */
@Service
class LogsService(var logsMapper: LogsMapper) : BaseService<Logs>() {
    override fun getMapper(): MPBaseMapper<Logs> {
        return logsMapper
    }
}