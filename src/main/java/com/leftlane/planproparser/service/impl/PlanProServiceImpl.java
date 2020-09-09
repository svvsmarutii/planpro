package com.leftlane.planproparser.service.impl;

import com.leftlane.planproparser.repository.PlanProRepository;
import com.leftlane.planproparser.repository.entities.PlanProEntity;
import com.leftlane.planproparser.repository.entities.PlanProEntityPK;
import com.leftlane.planproparser.repository.raw.PlanDataRepository;
import com.leftlane.planproparser.repository.raw.entities.PlanProEntityRaw;
import com.leftlane.planproparser.service.PlanProService;
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
public class PlanProServiceImpl implements PlanProService {

    @Autowired
    PlanProRepository planRepository;

    @Autowired
    PlanDataRepository planDataRepository = new PlanDataRepository();

    @Autowired
    AutoShutdownService shutdownService;

    @Async
    @Override
    public void parse() {
        try {
            List<PlanProEntityRaw> rawData = planDataRepository.getPlanData();
            while (!rawData.isEmpty()) {
                try {
                    insertPlanProData(rawData);
                    planDataRepository.updatePlanProEntity(rawData);
                    rawData = planDataRepository.getPlanData();
                } catch (Exception e) {
                    log.error("Error while copying the plan-pro records", e);
                }
            }


            List<PlanProEntityRaw> rawIncData = planDataRepository.getPlanIncData();
            while (!rawIncData.isEmpty()) {
                try {
                    insertPlanProIncData(rawIncData);
                    planDataRepository.updatePlanProEntity(rawIncData);
                    rawIncData = planDataRepository.getPlanIncData();
                } catch (Exception e) {
                    log.error("Error while copying the plan-pro-inc records", e);
                }
            }
        } catch (Exception e) {
            log.error("Error while copying the plan-pro parsing ", e);
        } finally {
            shutdownService.markPlanProDone();
        }

    }

    private void insertPlanProData(List<PlanProEntityRaw> rawDataList) throws Exception {
        List<PlanProEntity> planProEntities = new ArrayList<>();
        for (PlanProEntityRaw rawData : rawDataList) {
            PlanProEntity planProEntity = new PlanProEntity();
            BeanUtils.copyProperties(rawData, planProEntity);
            PlanProEntityPK pk = new PlanProEntityPK();
            pk.setId(rawData.getPk().getId());
            pk.setPlanId(rawData.getPlanId());
            pk.setAsOfDate(DateUtils.getDate2(rawData.getAsOfDate()));
            planProEntity.setPk(pk);
            planProEntity.setAsOf(DateUtils.getDate1(rawData.getAsOf()));
            planProEntity.setDeletedAt(DateUtils.getDate1(rawData.getDeletedAt()));
            planProEntities.add(planProEntity);
        }
        long startTime = System.currentTimeMillis();
        planRepository.saveAll(planProEntities);
        log.info("Total time taken PlanPro batch insert : {} ", System.currentTimeMillis() - startTime);
    }

    private void insertPlanProIncData(List<PlanProEntityRaw> rawDataList) throws Exception {
        List<PlanProEntity> planProEntities = new ArrayList<>();
        for (PlanProEntityRaw rawData : rawDataList) {
            PlanProEntity planProEntity = planRepository.findBypkPlanIdAndEin(rawData.getPlanId(), rawData.getEin());
            if (Objects.isNull(planProEntity)) {
                planProEntity = new PlanProEntity();
                PlanProEntityPK pk = new PlanProEntityPK();
                pk.setId(rawData.getPk().getId());
                pk.setPlanId(rawData.getPlanId());
                pk.setAsOfDate(DateUtils.getDate2(rawData.getAsOfDate()));
                planProEntity.setPk(pk);
            }
            BeanUtils.copyProperties(rawData, planProEntity, "pk");
            planProEntity.setAsOf(DateUtils.getDate1(rawData.getAsOf()));
            planProEntity.setDeletedAt(DateUtils.getDate1(rawData.getDeletedAt()));
            planProEntities.add(planProEntity);
        }
        long startTime = System.currentTimeMillis();
        planRepository.saveAll(planProEntities);
        log.info("Total time taken PlanPro INC batch insert : {} ", System.currentTimeMillis() - startTime);
    }
}