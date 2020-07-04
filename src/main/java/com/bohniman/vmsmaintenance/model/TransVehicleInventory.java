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
public class TransVehicleInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date assignedDate;

    private String assignedStatus;

    private Date returnedDate;

    private String returnStatus; // Returned/Damaged/Used/Lost

    private String itemOwner; // Vehicle Owner/ Police

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle")
    private MasterVehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle_inventory")
    private MasterVehicleInventory vehicle_inventory;

}