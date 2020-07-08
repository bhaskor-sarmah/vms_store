package com.bohniman.vmsmaintenance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterVendorItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Item Name is required")
    private String itemName;

    @NotBlank(message = "* Item details is required")
    private String itemDetails;

    @NotBlank(message = "* Item Unit is required")
    private String itemUnit;

    @NotBlank(message = "* Item price is required")
    private String itemPrice;

    @NotNull(message = "Vendor id required")
    @ManyToOne
    @JoinColumn(name = "fk_master_vendor_id")
    private MasterVendor masterVendor;
}