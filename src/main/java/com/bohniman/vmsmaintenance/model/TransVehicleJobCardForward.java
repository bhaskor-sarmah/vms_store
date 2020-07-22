package com.bohniman.vmsmaintenance.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransVehicleJobCardForward extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User forwardedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_card")
    private TransVehicleJobCard transVehicleJobCard;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean isCurrent = true;

}