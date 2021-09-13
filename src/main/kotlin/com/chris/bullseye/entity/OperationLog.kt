package com.chris.bullseye.entity

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author Chris
 * @date2020 12 01 17:32
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class OperationLog(
        /**
         * 业务名称
         *
         * @return
         */
        val value: String,
        /**
         * 对应的json格式参数
         * @return
         */
        val jsonParam: String = "",
        /**
         * 其它格式参数
         * @return
         */
        val param: String = "")
