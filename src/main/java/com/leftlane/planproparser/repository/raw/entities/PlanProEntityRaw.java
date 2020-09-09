package com.leftlane.planproparser.repository.raw.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "PLAN_PRO_RAW", schema = "reference_data")
public class PlanProEntityRaw {

    @EmbeddedId
    PlanPK pk;
    @Column(name = "as_of_date")
    private String asOfDate;
    @Column(name = "loadstatus")
    private String loadstatus;
    @Column(name = "plan_id")
    private String planId;

    @Column(name = "plan")
    private String plan;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "EIN")
    private Double ein;

    @Column(name = "plan_type")
    private String planType;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "year")
    private Integer year;

    @Column(name = "total_participants")
    private Integer totalParticipants;

    @Column(name = "total_assets")
    private Double totalAssets;

    @Column(name = "as_of")
    private String asOf;

    @Column(name = "deleted_at")
    private String deletedAt;
}
