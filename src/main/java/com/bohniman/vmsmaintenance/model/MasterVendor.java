package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Vendor Name is required")
    private String vendorName;

    @NotBlank(message = "* Vendor Details is required")
    private String vendorDetails;

    @NotBlank(message = "* Vendor Contract start date is required")
    private Date vendeorContractFromDate;

    @NotBlank(message = "* Vendor Contract end date is required")
    private Date vendeorContractToDate;

    private String vendorStatus;
}