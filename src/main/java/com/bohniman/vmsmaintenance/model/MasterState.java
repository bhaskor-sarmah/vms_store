package com.bohniman.vmsmaintenance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_state")
@Data
@NoArgsConstructor
public class MasterState {

    @Id
    private String stateCode;

    private String stateName;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private String isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<MasterDistrict> districts = new ArrayList<>();

}