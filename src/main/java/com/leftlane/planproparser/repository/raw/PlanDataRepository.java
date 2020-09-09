package com.leftlane.planproparser.repository.raw;

import com.leftlane.planproparser.repository.raw.entities.PIOEntityRaw;
import com.leftlane.planproparser.repository.raw.entities.PlanCustodiansRaw;
import com.leftlane.planproparser.repository.raw.entities.PlanProEntityRaw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.leftlane.planproparser.utils.Constants.*;


@Repository
@Slf4j
public class PlanDataRepository {

    @Autowired
    PlanProRawRepository planProRawRepo;

    @Autowired
    PIORawRepository pioRawRepo;

    @Autowired
    PlanCustodiansRawRepository planCustodiansRawRepo;

    @Value("${process.limit}")
    int limit;

    public List<PlanProEntityRaw> getPlanData() {
        log.debug("Picking PLAN_PRO_RAW records with status [" + NEW_FULL + "]");
        Pageable page = PageRequest.of(0, limit);
        return planProRawRepo.findByLoadstatus(NEW_FULL, page);
    }

    public List<PlanProEntityRaw> getPlanIncData() {
        log.debug("Picking PLAN_PRO_RAW records with status [" + NEW_INC + "]");
        Pageable page = PageRequest.of(0, limit);
        return planProRawRepo.findByLoadstatus(NEW_INC, page);
    }

    public List<PIOEntityRaw> getPlanInvestmentsData() {
        log.debug("Picking PLAN_INVESTMENT_OPTIONS_RAW records with status [" + NEW_FULL + "]");
        Pageable page = PageRequest.of(0, limit);
        return pioRawRepo.findByLoadstatus(NEW_FULL, page);
    }

    public List<PIOEntityRaw> getPlanInvestmentsIncData() {
        log.debug("Picking PLAN_INVESTMENT_OPTIONS_RAW records with status [" + NEW_INC + "]");
        Pageable page = PageRequest.of(0, limit);
        return pioRawRepo.findByLoadstatus(NEW_INC, page);
    }

    public List<PlanCustodiansRaw> getPlanCustodiansData() {
        log.debug("Picking PLAN_CUSTODIANS_RAW records with status [" + NEW_FULL + "]");
        Pageable page = PageRequest.of(0, limit);
        return planCustodiansRawRepo.findByLoadstatus(NEW_FULL, page);
    }

    public void updatePlanProEntity(List<PlanProEntityRaw> planProEntities) {
        log.info("Updating PLAN_PRO_RAW records status to [" + STAGED + "]");
        planProEntities.forEach(planProEntityRaw -> planProEntityRaw.setLoadstatus(STAGED));
        planProRawRepo.saveAll(planProEntities);
    }

    public void UpdatePlanInvestmentOptions(List<PIOEntityRaw> planInvestmentOptions) {
        log.debug("Updating PLAN_INVESTMENT_OPTIONS_RAW records status to [" + STAGED + "]");
        planInvestmentOptions.forEach(planProEntityRaw -> planProEntityRaw.setLoadstatus(STAGED));
        pioRawRepo.saveAll(planInvestmentOptions);
    }

    public void UpdatePlanCustodians(List<PlanCustodiansRaw> planCustodiansRaws) {
        log.debug("Updating PLAN_CUSTODIANS_RAW records status to [" + STAGED + "]");
        planCustodiansRaws.forEach(planProEntityRaw -> planProEntityRaw.setLoadstatus(STAGED));
        planCustodiansRawRepo.saveAll(planCustodiansRaws);
    }
}
