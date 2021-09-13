package com.chris.bullseye.system.controller

import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.Logs
import com.chris.bullseye.system.service.BaseService
import com.chris.bullseye.system.service.LogsService
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
class LogsController(var logsService: LogsService, jsonResult: JsonResult<Logs>){
}