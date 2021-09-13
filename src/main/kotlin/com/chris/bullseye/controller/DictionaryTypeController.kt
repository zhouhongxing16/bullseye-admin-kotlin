package com.chris.bullseye.controller

import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.DictionaryType
import com.chris.bullseye.pojo.FamousTeacher
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.DictionaryTypeService
import com.chris.bullseye.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Chris
 * @date 2020-12-23 17:57
 */
@Api(tags = ["字典数据"], produces = "字典数据")
@OperationLog("字典数据")
@RestController
@RequestMapping("/dictionarytype")
class DictionaryTypeController(var dictionaryTypeService: DictionaryTypeService,jsonResult: JsonResult<DictionaryType>):BaseController<DictionaryType>(jsonResult) {
    override fun service(): BaseService<DictionaryType> {
        return dictionaryTypeService
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    override fun create(@RequestBody obj: DictionaryType): JsonResult<DictionaryType> {
        var user  = AuthUtil.getCurrentUser()
        obj.userId = user!!.id
        return super.create(obj)
    }
}