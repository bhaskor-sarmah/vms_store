package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransChallan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "* Challan No is required")
    private String challanNo;

    // @NotNull(message = "* Challan No is required")
    private Double noOfItems;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "* Challan date is required")
    private Date challanDate;

    private Long totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_trans_bill")
    private TransBill transBill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_card_order", nullable = true)
    TransJobCardItemOrder order;
}