package com.bohniman.vmsmaintenance.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterShelvePayload {

    private Long id;

    private String shelveName;

    private String shelveDetails;

    private Long totalItems;
}