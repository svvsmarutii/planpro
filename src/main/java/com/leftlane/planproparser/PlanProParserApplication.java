package com.leftlane.planproparser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.leftlane")
public class PlanProParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanProParserApplication.class, args);
        log.info("APPLICATION PlanProParser STARTED");
    }
}
