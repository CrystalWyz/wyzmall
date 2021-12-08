package cn.wyz.wyzmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author wnx
 */
@Configuration
public class WyzmallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 跨域请求头
        corsConfiguration.addAllowedHeader("*");
        // 请求来源
        corsConfiguration.addAllowedOriginPattern("http://localhost:8001/");
        // 请求方式
        corsConfiguration.addAllowedMethod("*");
        // 跨域cookie
        corsConfiguration.setAllowCredentials(true);

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }
}
