package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MasterVehicle
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterVehicle extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String vehicleModel;

	@NotBlank(message = "Registration Number is required")
	private String vehicleRegistrationNo;

	@ManyToOne
	@JoinColumn(name = "fk_master_vehicle_type_id")
	private MasterVehicleType vehicleType;

	@ManyToOne
	@JoinColumn(name = "fk_master_vehicle_category_id")
	private MasterVehicleCategory vehicleCategory;

	@ManyToOne
	@JoinColumn(name = "fk_master_fuel_type_id")
	private MasterFuelType fuelType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_mto_details_id", updatable=false)
	private MasterMTODetails mto;

	@NotNull(message = "Mileage is required")
	private double mileage;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean status = true;

}