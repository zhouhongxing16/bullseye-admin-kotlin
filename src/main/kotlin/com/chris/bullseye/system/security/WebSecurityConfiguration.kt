package com.chris.bullseye.system.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * @author Chris
 * @date2020 12 02 16:53
 */
@Configuration
class WebSecurityConfiguration: WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.headers().frameOptions().sameOrigin() //允许页面中嵌套iframe
        http.csrf().disable() // 开启跨域
                .cors().and().authorizeRequests()
                .antMatchers("/unauth").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/imagevcode").permitAll()
                .antMatchers("/file/view/**").permitAll()
                .antMatchers("/file/download/**").permitAll()
                .antMatchers("/api/file/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .formLogin().loginProcessingUrl("/formLogin")
                .usernameParameter("username")
                .loginPage("/account/unauth").permitAll()
                .passwordParameter("password").defaultSuccessUrl("/home")
        http.logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login").permitAll()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        //解决静态资源被拦截的问题
        web.ignoring()
                .antMatchers("/assets/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/index.html")
                .antMatchers("/**.js")
                .antMatchers("/**.css")
                .antMatchers("/**.txt")
        web.ignoring().antMatchers("/doc.html", "/v3/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/medical-provider/api-docs")
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOriginPattern("*") // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*") // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*") // 3 设置访问源请求方法
        corsConfiguration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}