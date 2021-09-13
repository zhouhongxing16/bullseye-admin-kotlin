package com.chris.bullseye.common.config

import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.IAcsClient
import com.aliyuncs.exceptions.ClientException
import com.aliyuncs.exceptions.ServerException
import com.aliyuncs.profile.DefaultProfile
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AliConfig {

    @Value("\${aliConfig.OSS.endpoint}")
    var endpoint: String? = null

    @Value("\${aliConfig.OSS.accessKeyId}")
    var accessKeyId: String? = null

    @Value("\${aliConfig.OSS.accessKeySecret}")
    var accessKeySecret: String? = null

    @Value("\${aliConfig.OSS.bucketName}")
    var bucketName: String? = null

    @Value("\${aliConfig.OSS.domainName}")
    var domainName: String? = null

    @Value("\${aliConfig.video.accessKeyId}")
    var aliVideoAccessKeyId: String? = null

    @Value("\${aliConfig.video.accessKeySecret}")
    var aliVideoAccessKeySecret: String? = null
    var profile :DefaultProfile?=null
    var client: IAcsClient? = null
    fun initConfig(){
        var regionId =  "cn-shanghai"
         profile = DefaultProfile.getProfile(
                regionId,  // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                aliVideoAccessKeyId,  // 您的AccessKey ID
                aliVideoAccessKeySecret) // 您的AccessKey Secret
        client = DefaultAcsClient(profile)
    }






    fun getVideoPlayInfo(videoId:String?): GetPlayInfoResponse? {
        initConfig()
        val request = GetPlayInfoRequest()
        request.videoId = videoId
        var response = GetPlayInfoResponse()
        try {
            response = client!!.getAcsResponse(request)
        } catch (e: ServerException) {
            e.printStackTrace()
        } catch (e: ClientException) {
            e.printStackTrace()
            println("ErrCode:" + e.errCode)
            println("ErrMsg:" + e.errMsg)
            println("RequestId:" + e.requestId)
        }
        return response
    }

    fun getPlayUrl(videoId:String?):String? {
        initConfig()
        val request = GetPlayInfoRequest()
        // 视频ID。
       request.videoId = videoId
        var playUrl :String ?= null
        try {
            val response = client!!.getAcsResponse(request)
            for (playInfo in response.playInfoList) {
                // 播放地址
                println("PlayInfo.PlayURL = " + playInfo.playURL)
            }
            if(response.playInfoList.size>0){
                playUrl = response.playInfoList[0].playURL
            }
        } catch (e: ServerException) {
            e.printStackTrace()
        } catch (e: ClientException) {
            e.printStackTrace()
            println("ErrCode:" + e.errCode)
            println("ErrMsg:" + e.errMsg)
            println("RequestId:" + e.requestId)
        }
        return playUrl
    }

}