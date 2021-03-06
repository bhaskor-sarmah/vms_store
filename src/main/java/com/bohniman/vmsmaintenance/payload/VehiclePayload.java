package com.bohniman.vmsmaintenance.payload;

import com.bohniman.vmsmaintenance.model.FuelType;
import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;
import com.bohniman.vmsmaintenance.model.MasterVehicleType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehiclePayload {
	private Long id;

	private String vehicleModel;
	private String vehicleRegistrationNo;
	private MasterVehicleType vehicleType;
	private MasterVehicleCategory vehicleCategory;
	private FuelType fuelType;
	private double mileage;

	private Long latestJobCardId;
	private String latestJobCardStatus;

	private String scrappedReason;
	private String scrappedRemarks;
	private String scrappedStatus;
}