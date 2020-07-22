package com.bohniman.vmsmaintenance.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransVehicleJobCard extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicle")
    private MasterVehicle masterVehicle;

    @OneToMany(mappedBy = "transVehicleJobCard")
    private List<TransVehicleJobCardItems> items = new ArrayList<>();

    @OneToMany(mappedBy = "transVehicleJobCard")
    private List<TransVehicleJobCardForward> forwards = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date openedDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
	private Date cancelledDate = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date closedDate = new Date();

}