package com.bohniman.vmsmaintenance.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterRackPayload {

    private Long id;

    private String rackName;

    private String rackDetails;

    private Long totalShelves;

    private Long totalItems;
}