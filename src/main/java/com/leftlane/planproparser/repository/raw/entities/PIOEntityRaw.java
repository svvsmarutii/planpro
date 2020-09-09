package com.leftlane.planproparser.repository.raw.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "PLAN_INVESTMENT_OPTIONS_RAW", schema = "reference_data")
public class PIOEntityRaw {

    @Column(name = "as_of_date")
    private String asOfDate;

    @Column(name = "loadstatus")
    private String loadstatus;

    @EmbeddedId
    private PlanInvestmentsPK pk;

    @Column(name = "plan_id")
    private String planId;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "type")
    private String type;

    @Column(name = "fund")
    private String fund;

    @Column(name = "shareclass_match")
    private Integer shareClassMatch;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "asset_class")
    private String assetClass;

    @Column(name = "region")
    private String region;

    @Column(name = "style")
    private String style;

    @Column(name = "size")
    private String size;

    @Column(name = "proxy_ticker")
    private String proxyTicker;

    @Column(name = "deleted_at")
    private String deletedAt;

}
