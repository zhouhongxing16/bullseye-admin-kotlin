package com.chris.bullseye.common.service

import com.chris.bullseye.common.DTO.QuartzJobDTO
import com.chris.bullseye.common.config.SchedulerJobListener
import com.github.pagehelper.Page
import com.github.pagehelper.PageInfo
import com.github.pagehelper.util.PageObjectUtil
import org.quartz.*
import org.quartz.DateBuilder.IntervalUnit
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * @author Chris
 * @date 2021-10-08 11:25
 */
@Service
class QuartzService {

    @Autowired
    private val scheduler: Scheduler? = null

    private var jobListener: SchedulerJobListener? = null

    @PostConstruct
    fun startScheduler() {
        try {
            if (null == jobListener) {
                jobListener = SchedulerJobListener()
                scheduler!!.listenerManager.addJobListener(jobListener)
            }
            scheduler!!.start()
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     * 任务实现类
     * @param jobName
     * 任务名称
     * @param jobGroupName
     * 任务组名
     * @param interval
     * 时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes
     * 运行的次数 （<0:表示不限次数）
     */
    fun addJob(jobClass: Class<out QuartzJobBean?>, jobName: String, jobGroupName: String, interval: Int,
               jobTimes: Int) {
        try {
            val jobDetail = createJob(jobClass, jobName, jobGroupName)
            if (jobDetail != null) {
                // 使用simpleTrigger规则
                val trigger = createSimpleTrigger(jobName, jobGroupName, interval, jobTimes)
                scheduler!!.scheduleJob(jobDetail, trigger)
            }
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     * 任务实现类
     * @param jobName
     * 任务名称
     * @param jobGroupName
     * 任务组名
     * @param cronExpression
     * 时间表达式 （如：0/5 * * * * ? ）
     */
    fun addJob(jobClass: Class<out QuartzJobBean?>, jobName: String, jobGroupName: String, cronExpression: String) {
        try {
            // 把作业和触发器注册到任务调度中
            val jobDetail = createJob(jobClass, jobName, jobGroupName)
            if (jobDetail != null) {
                val trigger: Trigger = createCronTrigger(cronExpression, jobName, jobGroupName)
                scheduler!!.scheduleJob(jobDetail, trigger)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(SchedulerException::class)
    private fun createJob(jobClass: Class<*>, jobName: String, jobGroupName: String): JobDetail? {
        val jobKey = JobKey(jobName, jobGroupName)
        return if (scheduler!!.checkExists(jobKey)) null else JobBuilder.newJob(jobClass as Class<out Job>?).withIdentity(jobKey)
                .storeDurably(true) // 任务名称和组构成任务key
                .build()
        // 创建jobDetail实例，绑定Job实现类
        // 指明job的名称，所在组的名称，以及绑定job类
    }

    private fun createCronTrigger(cronExpression: String, jobName: String, jobGroupName: String): CronTrigger {
        //表达式调度构建器(即任务执行的时间)
        val scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
        //按新的cronExpression表达式构建一个新的trigger
        return TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .startAt(DateBuilder.futureDate(1, IntervalUnit.SECOND))
                .withSchedule(scheduleBuilder).build()
    }

    /**
     * 修改 一个job的 时间表达式
     *
     * @param jobName
     * @param jobGroupName
     * @param cronExpression
     */
    fun updateJob(jobName: String, jobGroupName: String, cronExpression: String) {
        try {
            val triggerKey = TriggerKey.triggerKey(jobName, jobGroupName)
            var trigger = scheduler!!.getTrigger(triggerKey) ?: return
            trigger = createCronTrigger(cronExpression, jobName, jobGroupName)

            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger)
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    fun updateJob(jobName: String, jobGroupName: String, interval: Int, times: Int) {
        try {
            val jobDetail = scheduler!!.getJobDetail(JobKey(jobName, jobGroupName))
            val objJobClass: Class<*>? = jobDetail.jobClass
            if (objJobClass != null) {
                removeJob(jobName, jobGroupName)
                addJob(objJobClass as Class<out QuartzJobBean?>, jobName, jobGroupName, interval, times)
            }
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    private fun removeJob(jobName: String, jobGroupName: String) {
        try {
            val triggerKey = TriggerKey.triggerKey(jobName, jobGroupName)
            scheduler!!.pauseTrigger(triggerKey)
            scheduler.unscheduleJob(triggerKey)
            deleteJob(jobName, jobGroupName)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun createSimpleTrigger(jobName: String, jobGroupName: String, interval: Int, times: Int): Trigger? {
        var trigger: Trigger? = null
        trigger = if (times <= 0) {
            TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)
                            .withIntervalInSeconds(interval))
                    .startNow().build()
        } else {
            TriggerBuilder
                    .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
                            .repeatSecondlyForever(1).withIntervalInSeconds(interval).withRepeatCount(times))
                    .startNow().build()
        }
        return trigger
    }

    /**
     * 删除任务一个job
     *
     * @param jobName
     * 任务名称
     * @param jobGroupName
     * 任务组名
     */
    fun deleteJob(jobName: String?, jobGroupName: String?) {
        try {
            scheduler!!.deleteJob(JobKey(jobName, jobGroupName))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 暂停一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    fun pauseJob(jobName: String?, jobGroupName: String?) {
        try {
            val jobKey = JobKey.jobKey(jobName, jobGroupName)
            scheduler!!.pauseJob(jobKey)
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    /**
     * 恢复一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    fun resumeJob(jobName: String?, jobGroupName: String?) {
        try {
            val jobKey = JobKey.jobKey(jobName, jobGroupName)
            scheduler!!.resumeJob(jobKey)
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    /**
     * 立即执行一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    fun runJobNow(jobName: String?, jobGroupName: String?) {
        try {
            val jobKey = JobKey.jobKey(jobName, jobGroupName)
            scheduler!!.triggerJob(jobKey)
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    fun queryAllJob(params: Map<String?, Any?>?): PageInfo<*>? {
        var jobList: MutableList<QuartzJobDTO?>? = null
        var page: PageInfo<*>? = null
        try {
            var start = 0
            var end = 0
            val matcher = GroupMatcher.anyJobGroup()
            val jobKeys: Array<Any> = scheduler!!.getJobKeys(matcher).toTypedArray()
            if (params != null) {
                val p: Page<*> = PageObjectUtil.getPageFromObject<Any>(params, true)
                start = p.startRow.toInt()
                end = start + p.pageSize
                if (end > jobKeys.size) {
                    end = jobKeys.size
                }
            } else {
                end = jobKeys.size
            }
            jobList = ArrayList<QuartzJobDTO?>()
            for (i in start until end) {
                val jobKey = jobKeys[i] as JobKey
                val triggers = scheduler.getTriggersOfJob(jobKey)
                val dto = QuartzJobDTO()
                dto.jobName = jobKey.name
                dto.jobGroup = jobKey.group
                dto.jobClass = jobKey.javaClass.name
                dto.jobStatus = "已过期"
                for (trigger in triggers) {
                    val triggerState = scheduler.getTriggerState(trigger.key)
                    dto.jobStatus =triggerState.name
                    if (trigger is SimpleTrigger) {
                        dto.intervar = trigger.repeatInterval.toInt()
                        dto.jobTimes = trigger.repeatCount
                    } else if (trigger is CronTrigger) {
                        val cronExpression = trigger.cronExpression
                        dto.cronExpression = cronExpression
                    }
                    break
                }
                jobList!!.add(dto)
            }
            page = PageInfo<Any?>(jobList as List<Any?>?)
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
        return page
    }

    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    fun queryRunJob(): List<Map<String?, Any?>?>? {
        var jobList: MutableList<Map<String?, Any?>?>? = null
        try {
            val executingJobs = scheduler!!.currentlyExecutingJobs
            jobList = ArrayList(executingJobs.size)
            for (executingJob in executingJobs) {
                val map: MutableMap<String?, Any?> = HashMap()
                val jobDetail = executingJob.jobDetail
                val jobKey = jobDetail.key
                val trigger = executingJob.trigger
                map["jobName"] = jobKey.name
                map["jobGroupName"] = jobKey.group
                map["description"] = "触发器:" + trigger.key
                val triggerState = scheduler.getTriggerState(trigger.key)
                map["jobStatus"] = triggerState.name
                if (trigger is CronTrigger) {
                    val cronExpression = trigger.cronExpression
                    map["jobTime"] = cronExpression
                }
                jobList.add(map)
            }
        } catch (e: SchedulerException) {
            e.printStackTrace()
        }
        return jobList
    }
}