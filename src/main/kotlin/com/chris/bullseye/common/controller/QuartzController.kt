package com.chris.bullseye.common.controller

import com.chris.bullseye.common.entity.response.QuartzJobResponse
import com.chris.bullseye.common.service.QuartzService
import com.chris.bullseye.system.entity.OperationLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.apache.commons.lang3.StringUtils
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.web.bind.annotation.*

/**
 * @author Chris
 * @date 2021-10-08 11:25
 */
@Api(tags = ["quartz"], produces = "任务调度")
@OperationLog("任务调度")
@RestController
@RequestMapping(value = ["/quartz"])
class QuartzController(val quartzService: QuartzService) {

    @ApiOperation(value = "添加任务", notes = "添加任务")
    @ApiImplicitParam(name = "添加任务")
    @OperationLog("添加任务")
    @ResponseBody
    @PostMapping("/addJob")
    fun startJob(@RequestBody job: QuartzJobResponse) {
        try {
            val cls = Class.forName(job.jobClass) as Class<out QuartzJobBean?>
            if (StringUtils.isNotEmpty(job.cronExpression)) {
                quartzService.addJob(cls, job.jobName!!, job.jobGroup!!, job.cronExpression!!)
            } else {
                quartzService.addJob(cls, job.jobName!!, job.jobGroup!!, job.intervar, job.jobTimes)
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    @ApiOperation(value = "更新任务", notes = "更新任务")
    @ApiImplicitParam(name = "更新任务")
    @OperationLog("更新任务")
    @PostMapping("/updateJob")
    fun updateJob(@RequestBody job: QuartzJobResponse) {
        if (StringUtils.isNotEmpty(job.cronExpression)) {
            quartzService.updateJob(job.jobName!!, job.jobGroup!!, job.cronExpression!!)
        } else {
            quartzService.updateJob(job.jobName!!, job.jobGroup!!, job.intervar, job.jobTimes)
        }
    }

    @ApiOperation(value = "删除任务", notes = "删除任务")
    @ApiImplicitParam(name = "删除任务")
    @OperationLog("删除任务")
    @PostMapping("/deleteJob")
    fun deleteJob(@RequestBody job: QuartzJobResponse) {
        quartzService.deleteJob(job.jobName, job.jobGroup)
    }

    @ApiOperation(value = "暂停任务", notes = "暂停任务")
    @ApiImplicitParam(name = "暂停任务")
    @OperationLog("暂停任务")
    @PostMapping("/pauseJob")
    fun pauseJob(@RequestBody job: QuartzJobResponse) {
        quartzService.pauseJob(job.jobName, job.jobGroup)
    }

    @ApiOperation(value = "恢复暂停任务", notes = "恢复暂停任务")
    @ApiImplicitParam(name = "恢复暂停任务")
    @OperationLog("恢复暂停任务")
    @PostMapping("/resumeJob")
    fun resumeJob(@RequestBody job: QuartzJobResponse) {
        quartzService.resumeJob(job.jobName, job.jobGroup)
    }

   /* @ApiOperation(value = "查询所有任务", notes = "查询所有任务")
    @ApiImplicitParam(name = "查询所有任务")
    @OperationLog("查询所有任务")
    @PostMapping("/queryAllJob")
    fun queryAllJob(@RequestBody params: Map<String?, Any?>?): Any? {
        return quartzService.queryAllJob(params)
    }*/

    @ApiOperation(value = "查询运行中任务", notes = "查询运行中任务")
    @ApiImplicitParam(name = "查询运行中任务")
    @OperationLog("查询运行中任务")
    @PostMapping("/queryRunJob")
    fun queryRunJob(): Any? {
        return quartzService.queryRunJob()
    }
}