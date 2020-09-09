package com.leftlane.planproparser.repository.raw;

import com.leftlane.planproparser.repository.raw.entities.PIOEntityRaw;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PIORawRepository extends JpaRepository<PIOEntityRaw, Long> {
    List<PIOEntityRaw> findByLoadstatus(String loadstatus, Pageable pageable);
}
