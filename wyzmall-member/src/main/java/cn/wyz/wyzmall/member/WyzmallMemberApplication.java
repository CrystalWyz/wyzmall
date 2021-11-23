package cn.wyz.wyzmall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("cn.wyz.wyzmall.member.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.wyz.wyzmall.member.feign")
public class WyzmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallMemberApplication.class, args);
    }

}
