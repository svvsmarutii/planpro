package com.leftlane.planproparser.repository.raw.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "PLAN_CUSTODIANS_RAW", schema = "reference_data")
public class PlanCustodiansRaw {

    @EmbeddedId
    PlanCustodiansPK pk;
    @Column(name = "as_of_date")
    private String asOfDate;
    @Column(name = "loadstatus")
    private String loadstatus;
    @Column(name = "plan_id")
    private String planId;

    @Column(name = "provider")
    private String provider;
}
