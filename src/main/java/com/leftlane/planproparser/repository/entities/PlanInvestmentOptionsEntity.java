package com.leftlane.planproparser.repository.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PLAN_INVESTMENT_OPTIONS", schema = "reference_data")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PlanInvestmentOptionsEntity {


    @EmbeddedId
    private PIOEntityPK pk;

    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "FUND")
    private String fund;

    @Column(name = "SHARE_CLASS_MATCH")
    private Integer shareClassMatch;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "ISSUER")
    private String issuer;

    @Column(name = "ASSET_CLASS")
    private String assetClass;

    @Column(name = "REGION")
    private String region;

    @Column(name = "STYLE")
    private String style;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "PROXY_TICKER")
    private String proxyTicker;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "VERSION")
    private Integer version;

}
