package com.bohniman.vmsmaintenance.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransVehicleInventoryLog  extends Auditable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalQuantity = 0.00;

    private Double quantityAssigned = 0.00;

    private Double quantityInStore = 0.00;

    private Double quantityDamaged = 0.00;

    private Double quantityReturned = 0.00;

    private Double quantityLost = 0.00;

    private Double quantityUsed = 0.00;

    private String action;

    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle", nullable = true)
    private MasterVehicle masterVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_inventory", nullable = false)
    private MasterVehicleInventory masterVehicleInventory;

}