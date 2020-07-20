
package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* Brand name is required")
    private String brandName;


    @NotBlank(message = "* Status is required")
    private String status; // ACTIVE , INACTIVE

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;
}