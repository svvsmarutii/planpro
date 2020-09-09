package com.leftlane.planproparser.repository.raw;

import com.leftlane.planproparser.repository.raw.entities.PlanProEntityRaw;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlanProRawRepository extends JpaRepository<PlanProEntityRaw, Long> {
    List<PlanProEntityRaw> findByLoadstatus(String loadstatus, Pageable pageable);
}