package cn.wyz.wyzmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.sql.DataSource;

/**
 * @author wnx
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WyzmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyzmallGatewayApplication.class, args);
    }

}
