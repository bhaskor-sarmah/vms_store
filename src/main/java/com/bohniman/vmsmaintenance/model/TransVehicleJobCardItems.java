package com.bohniman.vmsmaintenance.model;

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
public class TransVehicleJobCardItems extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle")
    private MasterVendorItem masterVendorItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_card")
    private TransVehicleJobCard transVehicleJobCard;

}