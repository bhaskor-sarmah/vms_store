package com.bohniman.vmsmaintenance.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapVehiclePayload {
    
    @NotNull(message = "Id is required")
    private Long vehicleId;

    @NotBlank(message = "* Reason Required")
    private String scrappedReason;

    @NotBlank(message = "* Remarks Required")
    private String scrappedRemarks;
}