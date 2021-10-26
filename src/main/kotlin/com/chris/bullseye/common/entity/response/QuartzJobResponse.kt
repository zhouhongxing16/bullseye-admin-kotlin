package com.chris.bullseye.common.entity.response

/**
 * @author Chris
 * @date 2021-10-08 11:39
 */
 data class QuartzJobResponse (
        var jobName: String? = null,

        var jobClass: String? = null,

        var jobGroup: String? = null,

        var cronExpression: String? = null,

        var intervar :Int =  0,

        var jobTimes :Int = 0,

        var jobStatus: String? = null,
)