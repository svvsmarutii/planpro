package com.leftlane.planproparser.controller;

import com.leftlane.planproparser.service.PlanCustodiansService;
import com.leftlane.planproparser.service.PlanInvestmentOptionsService;
import com.leftlane.planproparser.service.PlanProService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class PlanProController {

    @Autowired
    PlanProService planProService;

    @Autowired
    PlanInvestmentOptionsService planInvestmentOptionsService;

    @Autowired
    PlanCustodiansService planCustodiansService;

    @GetMapping("/planpro")
    public String parsePlanProData() {
        log.info("Request received to parse plan files");
        planProService.parse();

        log.info("Request received to parse plan investments files");
        planInvestmentOptionsService.parse();

        log.info("Request received to parse plan custodians files");
        planCustodiansService.parse();

        return "planpro Data parsing triggered - Async";
    }
}