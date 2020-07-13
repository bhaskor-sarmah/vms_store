package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterOldItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Item Name is required")
    private String itemName;

    @NotBlank(message = "* Item Details is required")
    private String itemDetails;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "* Replacement date is required")
    private Date replacedDate;

    @NotNull(message = "* Rack is required")
    @ManyToOne
    @JoinColumn(name = "fk_master_rack_id")
    private MasterRack masterRack;

    @NotNull(message = "* Shelve is required")
    @ManyToOne
    @JoinColumn(name = "fk_master_shelve_id")
    private MasterShelves masterShelves;

    @NotNull(message = "* Job Card is required")
    @ManyToOne
    @JoinColumn(name = "fk_trans_vehicle_jobcard_id")
    private TransVehicleJobCard transVehicleJobCard;

}
