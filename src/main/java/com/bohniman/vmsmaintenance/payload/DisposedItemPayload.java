package com.bohniman.vmsmaintenance.payload;

import lombok.Data;


@Data
public class DisposedItemPayload {
    private Long id;
    private String itemName;
    private Double quantity;
    private String unit;
    private String disposeReason;
    private Long rackId;
    private String rackName;
    private Long shelveId;
    private String shelveName;
}