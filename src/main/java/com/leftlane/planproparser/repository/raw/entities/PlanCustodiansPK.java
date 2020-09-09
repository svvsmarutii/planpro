package com.leftlane.planproparser.repository.raw.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PlanCustodiansPK implements Serializable {
    @Column(name = "runid")
    private Long runId;

    @Column(name = "id")
    private Long id;

    public PlanCustodiansPK(Long runId, Long id) {
        this.runId = runId;
        this.id = id;
    }

    public PlanCustodiansPK() {
    }
}
