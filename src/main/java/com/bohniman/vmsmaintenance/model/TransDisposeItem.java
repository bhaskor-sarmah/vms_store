package com.bohniman.vmsmaintenance.model;

import java.util.Date;

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
public class TransDisposeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date disposeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_master_item_brand")
    MasterItemBrand masterItemBrand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_trans_vehicle_jobcard")
    TransVehicleJobCard transVehicleJobCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rack")
    private MasterRack masterRack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_shelve")
    private MasterShelves masterShelve;
}