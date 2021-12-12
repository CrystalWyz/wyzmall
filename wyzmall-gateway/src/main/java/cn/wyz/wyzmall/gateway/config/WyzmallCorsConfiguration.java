package cn.wyz.wyzmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;

/**
 * @author wnx
 */
@Configuration
public class WyzmallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //跨域请求头
        corsConfiguration.addAllowedHeader("*");
        //跨域请求方法
        corsConfiguration.addAllowedMethod("*");
        //跨域请求源
        corsConfiguration.addAllowedOriginPattern("*");
        //跨域能否携带cookie
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        //跨域过滤器
        return new CorsWebFilter(source);
    }
}
