package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Name is required")
    private String itemName;

    private String itemDetails;

    @NotBlank(message = "* Select a category")
    private String itemCategory; // Consumable, Useable etc

    @NotBlank(message = "* Select a unit")
    private String itemUnit;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;
}