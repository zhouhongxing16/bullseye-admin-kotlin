package com.chris.bullseye.system.controller

import com.chris.bullseye.system.entity.JsonResult
import com.chris.bullseye.system.entity.OperationLog
import com.chris.bullseye.system.pojo.DictionaryType
import com.chris.bullseye.system.service.BaseService
import com.chris.bullseye.system.service.DictionaryTypeService
import com.chris.bullseye.common.utils.AuthUtil
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
class DictionaryTypeController(var dictionaryTypeService: DictionaryTypeService,jsonResult: JsonResult<DictionaryType>){
}