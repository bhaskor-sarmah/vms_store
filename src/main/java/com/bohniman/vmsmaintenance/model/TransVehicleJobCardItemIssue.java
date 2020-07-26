package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransVehicleJobCardItemIssue extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vendor_item")
    private TransVendorItem transVendorItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_card")
    private TransVehicleJobCard transVehicleJobCard;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;
}