package cn.wyz.wyzmall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("cn.wyz.wyzmall.order.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class WyzmallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallOrderApplication.class, args);
    }

}
