package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MasterVehiceType
 */
@Entity
@Data
@NoArgsConstructor
public class MasterVehicleType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleTypeId;

	@NotBlank(message = "Vehicle type name required")
	private String vehicleType;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean status = true;

	private String icon;

}