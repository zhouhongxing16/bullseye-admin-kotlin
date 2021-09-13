package com.chris.bullseye

import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class BullseyeApplication

fun main(args: Array<String>) {
    val ctx: ApplicationContext  = runApplication<BullseyeApplication>(*args)
    val logger: Logger = LoggerFactory.getLogger(BullseyeApplication::class.java)
    val activeProfiles = ctx.environment.activeProfiles
    logger.info("当前使用的 profile 是:{}", StringUtils.join(activeProfiles, "、"))
}
