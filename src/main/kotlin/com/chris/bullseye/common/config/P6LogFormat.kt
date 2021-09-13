package com.chris.bullseye.common.config

import com.chris.bulleyeadmin.common.override.SQLFormatter
import com.p6spy.engine.common.P6Util
import com.p6spy.engine.spy.appender.SingleLineFormat
import org.slf4j.LoggerFactory

class P6LogFormat : SingleLineFormat() {

    private val logger = LoggerFactory.getLogger(P6LogFormat::class.java)

    override fun formatMessage(connectionId: Int, now: String, elapsed: Long, category: String, prepared: String?, sql: String, url: String?): String? {
        var sql = sql
        val t = Thread.currentThread()
        sql = SQLFormatter.format(P6Util.singleLine(sql))
        logger.info(sql)
        return """
             $now|${t.name}|$elapsed|$category|connection $connectionId|
             Before Prepared SQL:$sql
             """.trimIndent()
    }
}
