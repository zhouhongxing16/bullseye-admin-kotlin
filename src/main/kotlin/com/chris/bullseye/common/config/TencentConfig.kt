package com.chris.bullseye.common.config

import com.tencentcloudapi.common.Credential
import com.tencentcloudapi.common.exception.TencentCloudSDKException
import com.tencentcloudapi.common.profile.ClientProfile
import com.tencentcloudapi.common.profile.HttpProfile
import com.tencentcloudapi.vod.v20180717.VodClient
import com.tencentcloudapi.vod.v20180717.models.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * @author Chris
 * @date 2021-01-15 9:59
 */

@Component
class TencentConfig {


    @Value("\${tencentConfig.video.secretId}")
    var secretId: String? = null

    @Value("\${tencentConfig.video.secretKey}")
    var secretKey: String? = null

    //accountId = 100017340906
    fun getVideoInfo(id:String?): MediaMetaData? {
        try {
            val cred = Credential(secretId, secretKey)
            val httpProfile = HttpProfile()
            httpProfile.endpoint = "vod.tencentcloudapi.com"
            val clientProfile = ClientProfile()
            clientProfile.httpProfile = httpProfile
            val client = VodClient(cred, "", clientProfile)
            val req = DescribeMediaInfosRequest()
            req.fileIds = arrayOf(id)
            val resp = client.DescribeMediaInfos(req)
            println(DescribeMediaInfosResponse.toJsonString(resp))
            if(resp.mediaInfoSet.isNullOrEmpty()){
                return null
            }else{
                return resp.mediaInfoSet[0].metaData
            }
        } catch (e: TencentCloudSDKException) {
            println(e.toString())
            return null
        }
    }

    //accountId = 100017340906
    fun getPlayUrl(id:String?): String? {
        try {
            val cred = Credential(secretId, secretKey)
            val httpProfile = HttpProfile()
            httpProfile.endpoint = "vod.tencentcloudapi.com"
            val clientProfile = ClientProfile()
            clientProfile.httpProfile = httpProfile
            val client = VodClient(cred, "", clientProfile)
            val req = DescribeMediaInfosRequest()
            req.fileIds = arrayOf(id)
            val resp = client.DescribeMediaInfos(req)
            println(DescribeMediaInfosResponse.toJsonString(resp))
            if(resp.mediaInfoSet.isNullOrEmpty()){
                return null
            }else{
                return resp.mediaInfoSet[0].basicInfo.mediaUrl
            }
        } catch (e: TencentCloudSDKException) {
            println(e.toString())
            return null
        }
    }
}