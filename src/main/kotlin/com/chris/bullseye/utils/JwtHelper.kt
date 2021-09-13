package com.chris.bullseye.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.chris.bullseye.entity.User
import com.chris.bullseye.pojo.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * @author Chris
 * @date2020 12 04 14:17
 */
class JwtHelper {

    companion object{
        /**
         * token 过期时间, 单位: 秒. 这个值表示 1 天
         */
        private val TOKEN_EXPIRED_TIME = (60 * 60 * 24 * 1000).toLong()

        /**
         * jwt 加密解密密钥
         */
        private val JWT_SECRET = "onlinebackend003"

        val jwtId = "tokenId"


        /**
         * 创建JWT
         */
        fun createJWT(subject: String): String? {
            val signatureAlgorithm = SignatureAlgorithm.HS512 //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
            val secretKey = generalKey()
            val nowMillis = System.currentTimeMillis() //生成JWT的时间
            val expMillis = nowMillis + TOKEN_EXPIRED_TIME
            //下面就是在为payload添加各种标准声明和私有声明了
            val builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                    //.setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                    //.setId(jwtId)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                    //.setIssuedAt(now)           //iat: jwt的签发时间
                    .setSubject(subject)
                    .setExpiration(Date(expMillis)) //设置过期时间
                    .signWith(signatureAlgorithm, secretKey) //设置签名使用的签名算法和签名使用的秘钥;
            return builder.compact()
        }

        /**
         * 验证jwt
         */
        fun verifyJwt(token: String): Claims? {
            //签名秘钥，和生成的签名的秘钥一模一样
            val key = generalKey()
            return try {
                Jwts.parser() //得到DefaultJwtParser
                        .setSigningKey(key) //设置签名的秘钥
                        .parseClaimsJws(token.replace("Bearer ", "")).body
            } catch (e: Exception) {
                null
            } //设置需要解析的jwt
        }


        /**
         * 由字符串生成加密key
         *
         * @return
         */
        fun generalKey(): SecretKey {
            val stringKey = JWT_SECRET
            val encodedKey = Base64.decodeBase64(stringKey)
            return SecretKeySpec(encodedKey, 0, encodedKey.size, "AES")
        }

        fun gener1alKey() {
            val stringKey = JWT_SECRET
            val encodedKey = Base64.decodeBase64(stringKey)
            println(encodedKey)
        }

        fun tokenToUser(token: String): User? {
            val stringKey = generalKey()
            val jsonObject = Jwts.parser().setSigningKey(stringKey).parseClaimsJws(token.replace("Bearer ", "")).body.subject
            val obj = JSON.parseObject(jsonObject)
            val ja =obj?.getString("authorities") as JSONArray
            val roleList  = obj.get("role") as List<Role>
            val user = User(obj.getString("username"), "", ja.toJavaList(GrantedAuthority::class.java))
            user.staffId = obj.getString("staffId")
            user.id = obj.getString("id")
            user.departmentId=obj.getString("departmentId")
            user.organizationId  = obj.getString("organizationId")
            user.roles = roleList
            return user
        }

        fun tokenToJSON(token: String): JSONObject? {
            val stringKey = generalKey()
            val jsonObject = Jwts.parser().setSigningKey(stringKey).parseClaimsJws(token.replace("Bearer ", "")).body.subject
            return JSON.parseObject(jsonObject)
        }
    }


}
