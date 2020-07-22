package com.bohniman.vmsmaintenance.payload;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class MasterItemBrandPayload {

    private Long id;

    private String itemName;

    private String itemBrand;

    private String unit;

    private String moq;
}