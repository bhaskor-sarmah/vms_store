package com.bohniman.vmsmaintenance.payload;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class TransItemPurchasePayload {

    private String name;
    private String unit;
    private Double quantity;
    private String warranty;

}