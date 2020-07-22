package com.bohniman.vmsmaintenance.payload;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class VendorOrderItemPayload {

    private Long orderId;

    private Long vendorId;

    private String vendorName;

    private Long jobCardId;

    private Double amount;

    private String status;
}