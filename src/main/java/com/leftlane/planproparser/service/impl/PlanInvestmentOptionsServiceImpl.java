package com.leftlane.planproparser.service.impl;

import com.leftlane.planproparser.repository.PlanInvestmentOptionsRepository;
import com.leftlane.planproparser.repository.entities.PIOEntityPK;
import com.leftlane.planproparser.repository.entities.PlanInvestmentOptionsEntity;
import com.leftlane.planproparser.repository.raw.PlanDataRepository;
import com.leftlane.planproparser.repository.raw.entities.PIOEntityRaw;
import com.leftlane.planproparser.service.PlanInvestmentOptionsService;
import com.leftlane.planproparser.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PlanInvestmentOptionsServiceImpl implements PlanInvestmentOptionsService {

    @Autowired
    PlanInvestmentOptionsRepository planInvestmentOptionsRepository;

    @Autowired
    PlanDataRepository planDataRepository;

    @Autowired
    AutoShutdownService shutdownService;

    @Async
    @Override
    public void parse() {
        try {
        List<PIOEntityRaw> rawData = planDataRepository.getPlanInvestmentsData();
        while (!rawData.isEmpty()) {
            try {
                insertPlanInvestmentOptionsData(rawData);
                planDataRepository.UpdatePlanInvestmentOptions(rawData);
                rawData = planDataRepository.getPlanInvestmentsData();
            } catch (Exception e) {
                log.error("Error while copying the plan-investment-options records", e);
            }
        }


        List<PIOEntityRaw> rawIncData = planDataRepository.getPlanInvestmentsIncData();
        while (!rawIncData.isEmpty()) {
            try {
                insertPlanInvestmentOptionsIncData(rawIncData);
                planDataRepository.UpdatePlanInvestmentOptions(rawIncData);
                rawIncData = planDataRepository.getPlanInvestmentsIncData();
            } catch (Exception e) {
                log.error("Error while copying the plan-investment-options-inc records", e);
            }
        }
        } catch (Exception e) {
            log.error("Error while copying the pio parsing ", e);
        } finally {
            shutdownService.markPIODone();
        }

    }

    private void insertPlanInvestmentOptionsData(List<PIOEntityRaw> pioEntityRawList) throws Exception {
        List<PlanInvestmentOptionsEntity> planInvestmentOptionsEntities = new ArrayList<>();
        for (PIOEntityRaw pioEntityRaw : pioEntityRawList) {
            PlanInvestmentOptionsEntity planInvestmentOptionsEntity = new PlanInvestmentOptionsEntity();
            BeanUtils.copyProperties(pioEntityRaw, planInvestmentOptionsEntity);
            PIOEntityPK pk = new PIOEntityPK();
            pk.setAsOfDate(DateUtils.getDate2(pioEntityRaw.getAsOfDate()));
            pk.setId(pioEntityRaw.getPk().getId());
            pk.setPlanId(pioEntityRaw.getPlanId());
            planInvestmentOptionsEntity.setPk(pk);
            planInvestmentOptionsEntity.setDeletedAt(DateUtils.getDate1(pioEntityRaw.getDeletedAt()));
            planInvestmentOptionsEntity.setVersion(1);
            planInvestmentOptionsEntities.add(planInvestmentOptionsEntity);
        }
        long startTime = System.currentTimeMillis();
        planInvestmentOptionsRepository.saveAll(planInvestmentOptionsEntities);
        log.info("Total time taken for Plan Investment batch insert : {} ", System.currentTimeMillis() - startTime);
    }

    private void insertPlanInvestmentOptionsIncData(List<PIOEntityRaw> pioEntityRawList) throws Exception {
        List<PlanInvestmentOptionsEntity> planInvestmentOptionsEntities = new ArrayList<>();
        for (PIOEntityRaw pioEntityRaw : pioEntityRawList) {
            PlanInvestmentOptionsEntity planInvestmentOptionsEntity = new PlanInvestmentOptionsEntity();
            PlanInvestmentOptionsEntity planInvestmentOptions = planInvestmentOptionsRepository.findTopBypkPlanIdOrderByVersionDesc(pioEntityRaw.getPlanId());
            Integer version = Objects.nonNull(planInvestmentOptions) && Objects.nonNull(planInvestmentOptions.getVersion()) ? planInvestmentOptions.getVersion() + 1 : 1;

            BeanUtils.copyProperties(pioEntityRaw, planInvestmentOptionsEntity);
            PIOEntityPK pk = new PIOEntityPK();
            pk.setAsOfDate(DateUtils.getDate2(pioEntityRaw.getAsOfDate()));
            pk.setId(pioEntityRaw.getPk().getId());
            pk.setPlanId(pioEntityRaw.getPlanId());
            planInvestmentOptionsEntity.setPk(pk);
            planInvestmentOptionsEntity.setDeletedAt(DateUtils.getDate1(pioEntityRaw.getDeletedAt()));
            planInvestmentOptionsEntity.setVersion(version);
            planInvestmentOptionsEntities.add(planInvestmentOptionsEntity);
        }
        long startTime = System.currentTimeMillis();
        planInvestmentOptionsRepository.saveAll(planInvestmentOptionsEntities);
        log.info("Total time taken for Plan Investment INC : {} ", System.currentTimeMillis() - startTime);
    }
}