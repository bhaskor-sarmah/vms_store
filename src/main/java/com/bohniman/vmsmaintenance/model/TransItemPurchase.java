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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransItemPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date purchaseDate;

    private Double puchaseQuantity;

    // private Long amount;

    private Date issueDate;

    private Long issueQuantity;

    private String itemStatus; // Fresh, Used, Auction, Scrap

    // private String partSlNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date warrantyUpto;

    private String itemType; // OLD , NEW

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    private TransJobCardItemOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_job_card")
    private TransVehicleJobCard jobCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vendor_item")
    private TransVendorItem transVendorItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_trans_challan")
    private TransChallan transChallan;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean isDeleted = false;

}