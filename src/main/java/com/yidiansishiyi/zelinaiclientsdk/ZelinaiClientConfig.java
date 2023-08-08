package com.yidiansishiyi.zelinaiclientsdk;

import com.yidiansishiyi.zelinaiclientsdk.client.ZelinaiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zelinai.client")
@Data
@ComponentScan
public class ZelinaiClientConfig {
    private String appkey;
    private String appsecret;

    @Bean
    public ZelinaiClient zelinaiClient(){
        return new ZelinaiClient(appkey,appsecret);
    }

}
