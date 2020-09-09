package com.leftlane.planproparser.starter;

import com.leftlane.planproparser.service.PlanCustodiansService;
import com.leftlane.planproparser.service.PlanInvestmentOptionsService;
import com.leftlane.planproparser.service.PlanProService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    PlanProService planProService;

    @Autowired
    PlanInvestmentOptionsService planInvestmentOptionsService;

    @Autowired
    PlanCustodiansService planCustodiansService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Request received to parse plan files");
        planProService.parse();

        log.info("Request received to parse plan investments files");
        planInvestmentOptionsService.parse();

        log.info("Request received to parse plan custodians files");
        planCustodiansService.parse();


    }
}