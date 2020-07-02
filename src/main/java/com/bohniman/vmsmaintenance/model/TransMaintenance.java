package com.bohniman.vmsmaintenance.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dateOfRequest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User requestedBy;

    private String kilometer;

    private String numberOfMonths; // Number of months of the vehicle (Age of Vehicle)

    private String nextServiceDate;

    private String nextServiceKilometer;

    private String status;

    private String approvalStatusHeadMachanic;

    private String approvalStatusMtoHead;

    @NotNull(message = "Maintenance Type is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_maintenance_type")
    private MaintenanceTypeMaster maintenanceType;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "join_transmaintenance_partsservice", joinColumns = @JoinColumn(name = "partsservice_id"), inverseJoinColumns = @JoinColumn(name = "transmaintanance_id"))
    private Set<PartsServiceMaster> partsServiceList;
}