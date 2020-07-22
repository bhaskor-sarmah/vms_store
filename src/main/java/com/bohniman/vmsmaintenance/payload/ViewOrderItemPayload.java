package com.bohniman.vmsmaintenance.payload;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class ViewOrderItemPayload {

    private String name;
    private String unit;
    private Double pricePerUnit;
    private Double price;
    private Double quantity;

}