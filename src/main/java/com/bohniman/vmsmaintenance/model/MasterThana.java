package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterThana {
    @Id
    private String thanaCode;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private String isActive;

    private String thanaName;

    private String districtName;
    private String districtCode;
}