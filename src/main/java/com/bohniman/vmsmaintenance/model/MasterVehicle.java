package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MasterVehicle
 */
@Entity
@Data
@NoArgsConstructor
public class MasterVehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String vehicleModel;

	@NotBlank(message = "Registration Number is required")
	private String vehicleRegistrationNo;

	@NotNull(message = "Category is required")
	private int categoryId;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean status = true;

	@NotNull(message = "Vehicle Type is required")
	@ManyToOne
	@JoinColumn(name = "fk_master_vehicle_type_id")
	private MasterVehicleType vehicleType;

	@OneToOne
	private FuelType fuelType;

	@ManyToOne
	@JoinColumn(name = "fk_mto_details")
	private MasterMTODetails mto;

	@ManyToOne
	private User owner;

	@CreationTimestamp
	private Date createdAt;

	private String modelName;

	private double mileage;

}