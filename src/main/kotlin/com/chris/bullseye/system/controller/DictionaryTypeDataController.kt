package com.chris.bullseye.system.controller

import com.chris.bullseye.system.dto.JsonResult
import com.chris.bullseye.system.dto.OperationLog
import com.chris.bullseye.system.pojo.DictionaryTypeData
import com.chris.bullseye.system.service.DictionaryTypeDataService
import com.chris.bullseye.common.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @author Chris
 * @date 2020-12-23 17:59
 */
@Api(tags = ["字典类型"], produces = "字典类型")
@OperationLog("字典数据")
@RestController
@RequestMapping("/dictionarydata")
class DictionaryTypeDataController(var dictionaryTypeDataService: DictionaryTypeDataService, val jsonResult: JsonResult<DictionaryTypeData>){

    @PostMapping("/create")
    @ApiOperation(value = "创建方法", notes = "")
    fun create(@RequestBody obj: DictionaryTypeData): JsonResult<DictionaryTypeData> {
        val user = AuthUtil.getCurrentUser()
        obj.creatorId = user!!.id
        obj.creatorName = user!!.name
        return dictionaryTypeDataService.add(obj)
    }

    @PostMapping("/getListByTypeCode/{code}")
    @ApiOperation(value = "根据类型code获取字典数据", notes = "")
    @Throws(Exception::class)
    fun getListByTypeCode(@PathVariable code: String): JsonResult<DictionaryTypeData> {
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["typeCode"] = code
        val list = dictionaryTypeDataService.getDtoListByParams(queryMap)
        return jsonResult.success(list,"查询成功！")
    }

    @ApiOperation(value = "根据字典code获取一条字典数据", notes = "")
    @PostMapping("/getDataByCode/{code}")
    @Throws(Exception::class)
    fun getDictionaryDataByCode(@PathVariable code: String): JsonResult<Any> {
        val queryMap: MutableMap<String, String?> = HashMap()
        queryMap["typeCode"] = code
        val dto = dictionaryTypeDataService.getByParams(queryMap)
        return JsonResult.success(dto,"查询成功")
    }
}