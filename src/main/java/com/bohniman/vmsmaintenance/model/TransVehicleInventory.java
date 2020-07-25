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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransVehicleInventory  extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private Date assignedDate = new Date();

    private String assignedStatus;

    private Date returnedDate = new Date();

    private Double quantity; 

    private Double quantityDamaged = 0.00;

    private Double quantityReturned = 0.00;

    private Double quantityLost = 0.00;

    private Double quantityUsed = 0.00;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle")
    private MasterVehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle_inventory")
    private MasterVehicleInventory vehicle_inventory;

}