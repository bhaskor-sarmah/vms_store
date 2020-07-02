package com.bohniman.vmsmaintenance.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "master_mto_details")
public class MasterMTODetails {

	@Id
	private long id;

	private String name;

	private String district;

}
