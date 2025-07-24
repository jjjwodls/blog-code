package com.spring.spring_cloud_config_client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class ConfigProperties {

    private String name1;
    private String name2;
}
