package com.bohniman.vmsmaintenance.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PartsServiceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String quantity;

    private String price;

    @NotNull(message = "Parts Type is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_parts_service_type")
    private PartsServiceTypeMaster type; // PARTS/SERVICE/DENTING_PAINTAING

    @NotNull(message = "Vendor is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_vender")
    private VendorMaster vendor;
}