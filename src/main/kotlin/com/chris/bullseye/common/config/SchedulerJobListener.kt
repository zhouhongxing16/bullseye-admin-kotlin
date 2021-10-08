package com.chris.bullseye.common.config

import com.chris.bullseye.common.utils.Logger
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.JobListener

/**
 * @author Chris
 * @date 2021-10-08 11:26
 */
class SchedulerJobListener : JobListener {
    val LISTENER_NAME = "QuartJobListener"

    override fun getName(): String? {
        return LISTENER_NAME //must return a name
    }

    //任务被调度前
    override fun jobToBeExecuted(context: JobExecutionContext) {
        val jobName = context.jobDetail.key.toString()
        Logger.debug("jobToBeExecuted")
        Logger.debug("Job : $jobName is going to start...")
    }

    //任务调度被拒了
    override fun jobExecutionVetoed(context: JobExecutionContext?) {
        Logger.debug("任务调度被拒了：jobExecutionVetoed")
        //可以做一些日志记录原因
    }

    //任务被调度后
    override fun jobWasExecuted(context: JobExecutionContext,
                                jobException: JobExecutionException?) {
        Logger.debug("jobWasExecuted")
        val jobName = context.jobDetail.key.toString()
        Logger.debug("Job : $jobName is finished...")
        if (jobException != null && jobException.message != "") {
            Logger.debug("Exception thrown by: " + jobName
                    + " Exception: " + jobException.message)
        }
    }
}