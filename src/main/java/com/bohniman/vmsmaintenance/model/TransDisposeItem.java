package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransDisposeItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Item Name is required")
    private String itemName;

    @NotNull(message = "* Quantity is required")
    private Double quantity;

    @NotBlank(message = "* Unit is required")
    private String unit;

    @NotBlank(message = "* Diapose Reason is required")
    private String disposeReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_trans_vehicle_jobcard")
    TransVehicleJobCard transVehicleJobCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rack")
    private MasterRack masterRack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_shelve")
    private MasterShelves masterShelve;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;
}