package cn.wyz.wyzmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("cn.wyz.wyzmall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class WyzmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallProductApplication.class, args);
    }

}
