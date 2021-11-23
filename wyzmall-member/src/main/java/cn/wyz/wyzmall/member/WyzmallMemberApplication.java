package cn.wyz.wyzmall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("cn.wyz.wyzmall.member.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class WyzmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallMemberApplication.class, args);
    }

}
