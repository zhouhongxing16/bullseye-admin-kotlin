package com.chris.bullseye.service

import com.chris.bullseye.basemapper.BaseMapper
import com.chris.bullseye.mapper.LogsMapper
import com.chris.bullseye.pojo.Logs
import org.springframework.stereotype.Service

/**
 * @author Chris
 * @date 2020 12 07 17:08
 */
@Service
class LogsService(var logsMapper: LogsMapper) : BaseService<Logs>() {
    override fun getMapper(): BaseMapper<Logs> {
        return logsMapper
    }
}