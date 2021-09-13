package com.chris.bullseye.controller

import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.Logs
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.LogsService
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Chris
 * @date 2020-12-24 9:22
 */

@Api(tags = ["日志"], produces = "日志")
@OperationLog("日志")
@RestController
@RequestMapping("/logs")
class LogsController(var logsService: LogsService, jsonResult: JsonResult<Logs>):BaseController<Logs>(jsonResult) {
    override fun service(): BaseService<Logs> {
        return logsService
    }
}