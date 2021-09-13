package com.chris.bullseye.security

/**
 * @author Chris
 * @date 2021-02-06 15:39
 */
import com.chris.bullseye.mapper.RoleMenuFunctionMapper
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import java.util.ArrayList
import javax.servlet.http.HttpServletRequest

@Component
class MyInvocationSecurityMetadataSourceService(var roleMenuFunctionMapper: RoleMenuFunctionMapper) : FilterInvocationSecurityMetadataSource {
    private var hashMap: HashMap<String?, MutableCollection<ConfigAttribute>>? = null

    /**
     * 加载权限表中所有权限，这里不想从数据库中获取直接写在了这
     */
    fun loadResourceDefine() {
        hashMap = HashMap<String?, MutableCollection<ConfigAttribute>>()
        var cfg: ConfigAttribute
        println("===============================")
        println("初始化加载")
        println("===============================")
        val roleMap: List<Map<String, String?>> = roleMenuFunctionMapper.getRoleAuthPaths();
        var array: MutableCollection<ConfigAttribute>
        for (map in roleMap) {
            array = ArrayList<ConfigAttribute>()
            //如果路径为空，则注入路径和角色
            if (hashMap!![map["path"]] == null) {
                cfg = SecurityConfig(map["code"])
                array.add(cfg)
                hashMap!![map["path"]] = array
            } else {
                //如果路径已注入，则判断有没有其它角色拥有这个路径
                if (hashMap!![map["path"]].toString() != map["role"].toString()) {
                    array = hashMap!![map["path"]]!!
                    cfg = SecurityConfig(map["code"])
                    array.add(cfg)
                } else {
                    cfg = SecurityConfig(map["code"])
                    array.add(cfg)
                }
                hashMap!![map["path"]] = array
            }
        }
        println(hashMap.toString())
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    override fun getAttributes(obj: Any): Collection<ConfigAttribute>? {
        if (hashMap == null) {
            loadResourceDefine()
        }
        //object 中包含用户请求的request 信息
        val request: HttpServletRequest = (obj as FilterInvocation).getHttpRequest()
        var matcher: AntPathRequestMatcher
        var resUrl: String
        val iter: Iterator<String?> = hashMap!!.keys.iterator()
        while (iter.hasNext()) {
            resUrl = iter.next().toString()
            //matches() 方法用于检测字符串是否匹配给定的正则表达式
            matcher = AntPathRequestMatcher(resUrl)
            //首先判断请求方法是否匹配，如果匹配继续判断是否对该用户授权
            if (matcher.matches(request)) {
                return hashMap!![resUrl]!!
            }
        }
        return null
    }

    override fun getAllConfigAttributes(): MutableCollection<ConfigAttribute>? {
        return null
    }


    override fun supports(aClass: Class<*>?): Boolean {
        //UsernamePasswordAuthenticationToken.class.equals(clazz);
        //return FilterInvocation.class.isAssignableFrom(aClass);
        return true
    }
}