package com.bohniman.vmsmaintenance.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobCardItemPayload {
    private Long id;

    private String itemName;

    private String brandName;

    private String vendorName;

    private String itemUnit;

    private Double pricePerUnit;

    private String moq;

    private Double quantity;
}