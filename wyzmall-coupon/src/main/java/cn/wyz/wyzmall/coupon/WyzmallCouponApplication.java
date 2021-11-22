package cn.wyz.wyzmall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.wyz.wyzmall.coupon.dao")
@SpringBootApplication
public class WyzmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallCouponApplication.class, args);
    }

}
