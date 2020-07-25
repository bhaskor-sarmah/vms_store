package com.bohniman.vmsmaintenance.payload;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class NewPurchaseItemPayload {

    private Long transVendorItemId;
    private Double quantityRemainingToReceive;
    private Double orderQuantity;
    private Double noOfItem;
    private Date warrantyUpto;
}