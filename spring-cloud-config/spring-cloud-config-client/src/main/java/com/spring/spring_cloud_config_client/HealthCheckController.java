package com.spring.spring_cloud_config_client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HealthCheckController {

    private final ConfigProperties configProperties;

    @GetMapping("")
    public String healthCheck(){
        log.info("rabbitMQ name1 = "  + configProperties.getName1());
        log.info("rabbitMQ name2 = "  + configProperties.getName2());
        return "hello";
    }
}
