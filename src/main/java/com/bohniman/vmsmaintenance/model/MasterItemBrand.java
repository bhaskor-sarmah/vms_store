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
public class MasterItemBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_master_item")
    private MasterItem item;

    @NotBlank(message = "* Minimum Order Quantity is required")
    private String moq; // Minimum Order Quantity in Units

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_brand")
    private MasterBrand brand;


    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;

}