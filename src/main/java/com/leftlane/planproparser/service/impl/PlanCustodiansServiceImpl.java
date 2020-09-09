package com.leftlane.planproparser.service.impl;

import com.leftlane.planproparser.repository.PlanCustodiansRepository;
import com.leftlane.planproparser.repository.entities.PlanCustodians;
import com.leftlane.planproparser.repository.entities.PlanCustodiansPK;
import com.leftlane.planproparser.repository.raw.PlanDataRepository;
import com.leftlane.planproparser.repository.raw.entities.PlanCustodiansRaw;
import com.leftlane.planproparser.service.PlanCustodiansService;
import com.leftlane.planproparser.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PlanCustodiansServiceImpl implements PlanCustodiansService {

    @Autowired
    PlanCustodiansRepository custodiansRepository;

    @Autowired
    PlanDataRepository planDataRepository = new PlanDataRepository();

    @Autowired
    AutoShutdownService shutdownService;

    @Async
    @Override
    public void parse() {
        try {
            List<PlanCustodiansRaw> rawData = planDataRepository.getPlanCustodiansData();
            while (!rawData.isEmpty()) {
                try {
                    insertPlanProData(rawData);
                    planDataRepository.UpdatePlanCustodians(rawData);
                    rawData = planDataRepository.getPlanCustodiansData();
                } catch (Exception e) {
                    log.error("Error while copying the plan-custodians records", e);
                }
            }
        } catch (Exception e) {
            log.error("Error while copying the plan-custodians parsing ", e);
        } finally {
            shutdownService.markPlanCustodiansDone();
        }

    }

    private void insertPlanProData(List<PlanCustodiansRaw> rawDataList) throws Exception {
        List<PlanCustodians> planCustodians = new ArrayList<>();
        for (PlanCustodiansRaw rawData : rawDataList) {
            PlanCustodians custodians = new PlanCustodians();
            PlanCustodiansPK pk = new PlanCustodiansPK();
            pk.setId(rawData.getPk().getId());
            pk.setPlanId(rawData.getPlanId());
            pk.setAsOfDate(DateUtils.getDate2(rawData.getAsOfDate()));
            custodians.setPk(pk);
            custodians.setProvider(rawData.getProvider());
            planCustodians.add(custodians);
        }
        long startTime = System.currentTimeMillis();
        custodiansRepository.saveAll(planCustodians);
        log.info("Total time taken for PlanCustodians batch insert: {} ", System.currentTimeMillis() - startTime);
    }
}