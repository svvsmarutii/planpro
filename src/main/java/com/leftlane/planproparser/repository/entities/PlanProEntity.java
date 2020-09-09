package com.leftlane.planproparser.repository.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PLAN_PRO", schema = "reference_data")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PlanProEntity {

    @EmbeddedId
    private PlanProEntityPK pk;

    @Column(name = "PLAN")
    private String plan;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "EIN")
    private Double ein;

    @Column(name = "PLAN_TYPE")
    private String planType;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "TOTAL_PARTICIPANTS")
    private Integer totalParticipants;

    @Column(name = "TOTAL_ASSETS")
    private Double totalAssets;

    @Column(name = "AS_OF")
    private Date asOf;

    @Column(name = "DELETED_AT")
    private Date deletedAt;

}
