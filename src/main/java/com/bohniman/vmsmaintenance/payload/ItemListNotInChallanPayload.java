package com.bohniman.vmsmaintenance.payload;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class ItemListNotInChallanPayload {

    private Long transVendorItemId;

    private String itemName;

    private String itemUnit;

    private Double orderQuantity;

    private Double quantityRemainingToReceive;

    private Long challanId;
}