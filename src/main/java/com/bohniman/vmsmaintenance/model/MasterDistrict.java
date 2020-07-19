package com.bohniman.vmsmaintenance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_district")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterDistrict {

    @Id
    private String districtCode;

    private String districtName;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private String isActive;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_state")
    private MasterState state;

}