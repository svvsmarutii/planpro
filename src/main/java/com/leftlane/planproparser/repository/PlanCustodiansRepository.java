package com.leftlane.planproparser.repository;

import com.leftlane.planproparser.repository.entities.PlanCustodians;
import com.leftlane.planproparser.repository.entities.PlanCustodiansPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCustodiansRepository extends JpaRepository<PlanCustodians, PlanCustodiansPK> {
}
