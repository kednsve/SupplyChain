package com.supply_chain.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                    // 所有接口
                .allowedOriginPatterns("*")           // 允许所有域名（开发环境）
                // .allowedOrigins("http://localhost:5173") // 生产环境建议指定域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)               // 允许携带 cookie / token
                .maxAge(3600);                        // 预检请求缓存时间
    }
}