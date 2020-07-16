package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransVendorItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Price per unit is required")
    private Long pricePerUnit;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isApprovedItem = false;

    private String currentStatus; // ACTIVE , INACTIVE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_master_item")
    private MasterItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vendor")
    private MasterVendor masterVendor;
}