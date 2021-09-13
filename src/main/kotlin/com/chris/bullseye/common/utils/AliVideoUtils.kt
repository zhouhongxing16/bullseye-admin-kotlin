package com.chris.bullseye.common.utils


/**
 * @author Chris
 * @date 2021-01-08 9:56
 */
class AliVideoUtils {

   /* companion object{
         var accessKeyId = "LTAI4GKQvL4JtzU9D8Y8KN3n" // 您的AccessKey ID
         var accessKeySecret =  "oNe8rR2KpcwNYChtWc1fkawiHLsoVj"
         var regionId =  "cn-shanghai"
        val profile = DefaultProfile.getProfile(
                regionId,  // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                accessKeyId,  // 您的AccessKey ID
                accessKeySecret) // 您的AccessKey Secret


        val client: IAcsClient = DefaultAcsClient(profile)
        val request = GetPlayInfoRequest()

        fun getVideoPlayInfo(videoId:String?):GetPlayInfoResponse? {
            request.videoId = videoId
            var response = GetPlayInfoResponse()
            try {
                 response = client.getAcsResponse(request)
            } catch (e: ServerException) {
                e.printStackTrace()
            } catch (e: ClientException) {
                println("ErrCode:" + e.errCode)
                println("ErrMsg:" + e.errMsg)
                println("RequestId:" + e.requestId)
            }
            return response
        }

        fun getPlayUrl(videoId:String?):String? {
            // 视频ID。
            request.videoId = videoId
            var playUrl :String ?= null
            try {
                val response = client.getAcsResponse(request)
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
                println("ErrCode:" + e.errCode)
                println("ErrMsg:" + e.errMsg)
                println("RequestId:" + e.requestId)
            }
            return playUrl
        }
    }*/


}