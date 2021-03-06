package com.bohniman.vmsmaintenance.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class MasterVehicleInventory extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="* Name is required")
    private String name;

    @NotBlank(message="* Select a category")
    private String category;

    @NotBlank(message="* Select a unit")
    private String unit;

    private Double totalQuantity = 0.00;

    private Double quantityAssigned = 0.00;

    private Double quantityInStore = 0.00;

    private Double quantityDamaged = 0.00;

    private Double quantityReturned = 0.00;

    private Double quantityLost = 0.00;

    private Double quantityUsed = 0.00;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;

}