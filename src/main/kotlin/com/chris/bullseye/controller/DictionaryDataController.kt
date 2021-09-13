package com.chris.bullseye.controller

import com.github.pagehelper.PageInfo
import com.chris.bullseye.entity.JsonResult
import com.chris.bullseye.entity.OperationLog
import com.chris.bullseye.pojo.DictionaryData
import com.chris.bullseye.service.BaseService
import com.chris.bullseye.service.DictionaryDataService
import com.chris.bullseye.utils.AuthUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
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
class DictionaryDataController(var dictionaryDataService: DictionaryDataService,jsonResult: JsonResult<DictionaryData>):BaseController<DictionaryData>(jsonResult) {
    override fun service(): BaseService<DictionaryData> {
        return dictionaryDataService
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建方法", notes = "")
    @ApiImplicitParam(name = "obj", value = "")
    override fun create(@RequestBody obj: DictionaryData): JsonResult<DictionaryData> {
        val user = AuthUtil.getCurrentUser()
        obj.userId= user!!.id
        return dictionaryDataService.add(obj)
    }

    @PostMapping("/getListByTypeCode/{code}")
    @ApiOperation(value = "根据类型code获取字典数据", notes = "")
    @ApiImplicitParam(name = "code", value = "")
    @Throws(Exception::class)
    fun getListByTypeCode(@PathVariable code: String): JsonResult<DictionaryData> {
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["typeCode"] = code
        val list = dictionaryDataService.getDtoListByParams(queryMap)
        var pageInfo = PageInfo(list)
        return jsonResult.successPageList(list, pageInfo.total,pageInfo.isIsLastPage,"查询成功！")
    }

    @ApiOperation(value = "根据字典code获取一条字典数据", notes = "")
    @ApiImplicitParam(name = "code", value = "")
    @PostMapping("/getDataByCode/{code}")
    @Throws(Exception::class)
    fun getDictionaryDataByCode(@PathVariable code: String): JsonResult<Any> {
        val queryMap: MutableMap<String, String?> = HashMap()
        queryMap["typeCode"] = code
        val dto = dictionaryDataService.getByParams(queryMap)
        return JsonResult.success(dto,"查询成功")
    }
}