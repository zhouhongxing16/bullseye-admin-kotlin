package com.chris.bullseye.system.service

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.chris.bullseye.common.utils.AuthUtil
import com.chris.bullseye.common.utils.IPUtils
import com.chris.bullseye.common.utils.Logger
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.Logs
import org.apache.commons.lang3.StringUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.net.URLDecoder
import java.time.LocalDateTime
import java.util.*

/**
 * @author Chris
 * @date 2020-12-11 17:21
 */

//记录用户操作日志
@Aspect
@Component
class LoggerAopService(var logsService: LogsService) {


    /*  @Pointcut("execution(* com.chris.bulleyeadmin.*.controller.*.*(..))")
    public void show() {
    }

    @Before("show()")
    public void Before(JoinPoint jp) {
        try {
            Class clz = jp.getTarget().getClass();
            String optionName = "";
            if (clz.isAnnotationPresent( OperationLog.class )) {
                OperationLog opera = (OperationLog) clz.getAnnotation( OperationLog.class );
                optionName = opera.value();
            }
            if (StringUtils.isEmpty( optionName )) {
                return;
            }
            Method[] flds = jp.getTarget().getClass().getMethods();
            String optionType = "";
            for (int i = 0; i < flds.length; i++) {
                if (flds[i].isAnnotationPresent( OperationLog.class ) && flds[i].getName().equals( jp.getSignature().getName() )) {
                    OperationLog opera = flds[i].getAnnotation( OperationLog.class );
                    optionType = opera.value();
                }
            }
            if (StringUtils.isEmpty( optionType )) {
                return;
            }
            Object data = jp.getArgs();
            String ss = "";
            try {
                ss = jsonMapper.writeValueAsString( data ).trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            Logs logs = new Logs();
            logs.setStatus(1);
            logs.setOptionType( optionType );
            logs.setOptionName( optionName );

            if(AuthUtil.getCurrentUser() != null){
                logs.setOrganizationId( AuthUtil.getCurrentUser().getOrganizationId() );
                logs.setUserId( AuthUtil.getCurrentUser().getId() );
            }
            if(!StringUtils.isEmpty( ss )) {
                String content = ss.substring( 1, ss.length() - 1 );
                logs.setParams( Logger.formatJson( content ) );
            }
            logsService.add( logs );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @After("show())")
    public void After(JoinPoint jp) {
//        AuthUtil.local.remove();
    }*/
    @Pointcut("execution(* com.chris.bullseye.system.controller.*.*(..)))")
    fun logPointCut() {
    }

    @Around("logPointCut()")
    fun around(point: ProceedingJoinPoint): Any? {
        val beginTime = System.currentTimeMillis()
        // 执行方法
        val result = point.proceed()
        // 执行时长(毫秒)
        val time = System.currentTimeMillis() - beginTime
        //异步保存日志
        saveLog(point, time)
        return result
    }

    fun saveLog(joinPoint: ProceedingJoinPoint, time: Long) {
        val ra = RequestContextHolder.getRequestAttributes()
        val sra = ra as ServletRequestAttributes?
        val req = sra!!.request
        val methodType = req.method

        val signature = joinPoint.signature as MethodSignature
        val clz: Class<*> = joinPoint.target.javaClass
        var optionName = ""
        if (clz.isAnnotationPresent(OperationLog::class.java)) {
            val opera = clz.getAnnotation(OperationLog::class.java) as OperationLog
            optionName = opera.value
        }
        if (StringUtils.isEmpty(optionName)) {
            return
        }
        val flds = joinPoint.target.javaClass.methods
        var optionType = ""
        for (i in flds.indices) {
            if (flds[i].isAnnotationPresent(OperationLog::class.java) && flds[i].name == joinPoint.signature.name) {
                val opera: OperationLog = flds[i].getAnnotation(OperationLog::class.java)
                optionType = opera.value
            }
        }
        val logs = Logs()
        logs.status = 1
        logs.optionType = optionType
        logs.optionName = optionName
        // 请求的方法名
        val className = joinPoint.target.javaClass.name
        val methodName = signature.name
        logs.method = "$className.$methodName()"
        // 请求的参数
        val args = joinPoint.args
        var params: String? = null
        var queryString: String? = req.queryString
        if (!queryString.isNullOrEmpty()) {
            queryString = URLDecoder.decode(req.queryString, "UTF-8")
        }
        //获取请求参数集合并进行遍历拼接
        if (!args.isNullOrEmpty()) {
            if (RequestMethod.POST.toString() == methodType) {
                val obj = args[0]
                val map: Map<String, Any>? = this.getKeyAndValue(obj)
                params = JSON.toJSONString(map)
            } else if (RequestMethod.GET.toString() == methodType || RequestMethod.DELETE.toString() == methodType) {
                params = queryString
            }

            logs.params = params
        }
        if (AuthUtil.getCurrentUser() != null) {
            logs.organizationId = AuthUtil.getCurrentUser()?.organizationId
            logs.userId = AuthUtil.getCurrentUser()?.id
        }
        /*if (!StringUtils.isEmpty(params)) {
            val content = params!!.substring(1, params.length - 1)
            logs.params = Logger.formatJson(content)
        }*/
        // 获取request
        // 设置IP地址
        logs.ip = IPUtils.getIpAddr(req)
        // 系统当前时间
        logs.createTime = LocalDateTime.now()
        logs.executionTime = time.toInt()
        // 保存系统日志
        logsService.add(logs)
    }


    /***
     * 解析参数
     * @param obj
     * @return
     */
    private fun getKeyAndValue(obj: Any): Map<String, Any>? {
        val map: MutableMap<String, Any> = HashMap(32)
        // 得到类对象
        val userCla: Class<*> = obj.javaClass
        /* 得到类中的所有属性集合 */
        val fsArray = userCla.declaredFields
        for (fs in fsArray) {
            fs.isAccessible = true // 设置些属性是可以访问的
            var `val`: Any
            try {
                `val` = fs[obj]
                // 得到此属性的值
                map[fs.name] = `val` // 设置键值
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return map
    }
}