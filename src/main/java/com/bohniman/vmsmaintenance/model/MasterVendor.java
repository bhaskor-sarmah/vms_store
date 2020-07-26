package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ========================================================================
    // VENDOR PERSONAL DETAILS
    // ========================================================================

    @NotBlank(message = "* Vendor Name is required")
    private String vendorName;

    // @NotBlank(message = "* Vendor Name is required")
    private String vendorDetails;

    @NotBlank(message = "* Vendor Mobile is required")
    private String vendorMobile;

    @Email
    private String vendorEmail;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "* Vendor Contract start date is required")
    private Date vendorContractFromDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "* Vendor Contract end date is required")
    private Date vendorContractToDate;

    // ========================================================================
    // VENDOR BANK DETAILS
    // ========================================================================

    @NotBlank(message = "* Bank Account No is required")
    private String accountNo;

    @NotBlank(message = "* IFSC Code is required")
    private String ifscCode;

    @NotBlank(message = "* Bank Name is required")
    private String bankName;

    @NotBlank(message = "* Branch is required")
    private String branch;

    // ========================================================================
    // VENDOR REGISTERED OFFICE
    // ========================================================================

    @NotBlank(message = "* House No is required")
    private String houseNo;

    @NotBlank(message = "* Road/Street is required")
    private String roadStreet;

    @NotBlank(message = "* Locality is required")
    private String locality;

    @NotBlank(message = "* District is required")
    private String district;

    @NotBlank(message = "* State is required")
    private String state;

    @NotBlank(message = "* Pincode is required")
    private String pincode;

    // ========================================================================
    // VENDOR BILLING OFFICE
    // ========================================================================
    @NotBlank(message = "* House No is required")
    private String billHouseNo;

    @NotBlank(message = "* Road/Street is required")
    private String billRoadStreet;

    @NotBlank(message = "* Locality is required")
    private String billLocality;

    @NotBlank(message = "* District is required")
    private String billDistrict;

    @NotBlank(message = "* State is required")
    private String billState;

    @NotBlank(message = "* Pincode is required")
    private String billPincode;

    // ========================================================================
    // VENDOR OTHER DETAILS
    // ========================================================================
    private String vendorStatus; // ACTIVE , INACTIVE -> DELETED

    @NotBlank(message = "* Pincode is required")
    private String vendorCategory; // CASH PURCHASE , TENDER ITEM

    @NotBlank(message = "* GST No is required")
    private String gstNo;

    @NotBlank(message = "* Pan No is required")
    private String panNo;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;
}