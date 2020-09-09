package com.leftlane.planproparser.repository.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLAN_CUSTODIANS", schema = "reference_data")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PlanCustodians {

    @EmbeddedId
    private PlanCustodiansPK pk;

    @Column(name = "PROVIDER")
    private String provider;
}
