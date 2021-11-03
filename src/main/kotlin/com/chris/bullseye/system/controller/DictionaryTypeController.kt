package com.chris.bullseye.system.controller

import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.DictionaryType
import com.chris.bullseye.system.service.DictionaryTypeService
import io.swagger.annotations.Api
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