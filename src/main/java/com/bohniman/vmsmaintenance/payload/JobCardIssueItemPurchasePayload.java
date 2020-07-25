package com.bohniman.vmsmaintenance.payload;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItemIssue;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobCardIssueItemPurchasePayload {

    private Double orderQuantity;

    private Double purchaseQuantity;

    private Double remainingQuantity;

    private Long jobCardId;

    private Long transVendorItemId;

    private String itemName;

    private String itemUnit;

    private List<TransVehicleJobCardItemIssue> item = new ArrayList<>(); 
}