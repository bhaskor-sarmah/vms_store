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
 * MasterVehicleCategory
 */
@Entity
@Data
@NoArgsConstructor
public class MasterVehicleCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleCategoryId;

	@NotBlank(message = "Vehicle category name required")
	private String title;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean status = true;

	private String icon;

}