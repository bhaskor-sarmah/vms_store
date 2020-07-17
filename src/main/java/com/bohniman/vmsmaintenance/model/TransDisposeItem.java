package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransDisposeItem {

    private Date disposeDate;

    MasterItemBrand masterItem;

    TransVehicleJobCard transVehicleJobCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rack")
    private MasterRack masterRack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_shelve")
    private MasterShelves masterShelve;
}