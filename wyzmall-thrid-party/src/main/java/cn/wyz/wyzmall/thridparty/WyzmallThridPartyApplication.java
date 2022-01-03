package cn.wyz.wyzmall.thridparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WyzmallThridPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallThridPartyApplication.class, args);
    }

}
