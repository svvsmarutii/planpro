package com.leftlane.planproparser.repository;

import com.leftlane.planproparser.repository.entities.PIOEntityPK;
import com.leftlane.planproparser.repository.entities.PlanInvestmentOptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanInvestmentOptionsRepository extends JpaRepository<PlanInvestmentOptionsEntity, PIOEntityPK> {

    PlanInvestmentOptionsEntity findTopBypkPlanIdOrderByVersionDesc(String planId);

}
