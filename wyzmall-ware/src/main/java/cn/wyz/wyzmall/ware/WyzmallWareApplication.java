package cn.wyz.wyzmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("cn.wyz.wyzmall.ware.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class WyzmallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallWareApplication.class, args);
    }

}
