package com.leftlane.planproparser.repository.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class PIOEntityPK implements Serializable {

    @Column(name = "asofdate")
    private Date asOfDate;

    @Column(name = "ID")
    private Long id;

    @Column(name = "PLAN_ID")
    private String planId;

    public PIOEntityPK(Date asOfDate, Long id, String planId) {
        this.asOfDate = asOfDate;
        this.id = id;
        this.planId = planId;
    }

    public PIOEntityPK() {
    }

}
