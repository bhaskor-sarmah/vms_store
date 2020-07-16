package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    private String itemStatus; // Auction, Scrap, Transfer\

    private String partSlNo;

    private Date warrantyUpto;

    private String itemType; // OLD , NEW

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rack")
    private MasterRack masterRack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_shelve")
    private MasterShelves masterShelve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vendor_item")
    private TransVendorItem transVendorItem;

}