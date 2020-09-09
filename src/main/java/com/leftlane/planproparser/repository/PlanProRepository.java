package com.leftlane.planproparser.repository;

import com.leftlane.planproparser.repository.entities.PlanProEntity;
import com.leftlane.planproparser.repository.entities.PlanProEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanProRepository extends JpaRepository<PlanProEntity, PlanProEntityPK> {

    PlanProEntity findBypkPlanIdAndEin(String planId, Double ein);
}
