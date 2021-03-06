package cn.wyz.wyzmall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("cn.wyz.wyzmall.coupon.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class WyzmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallCouponApplication.class, args);
    }

}
