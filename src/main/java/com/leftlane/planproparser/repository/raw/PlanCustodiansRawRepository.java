package com.leftlane.planproparser.repository.raw;

import com.leftlane.planproparser.repository.raw.entities.PlanCustodiansRaw;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlanCustodiansRawRepository extends JpaRepository<PlanCustodiansRaw, Long> {
    List<PlanCustodiansRaw> findByLoadstatus(String loadstatus, Pageable pageable);
}