package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVehicleRepository extends JpaRepository<MasterVehicle, Long> {

    List<MasterVehicle> findAllByVehicleRegistrationNo(String vehicleNo);

	List<MasterVehicle> findByStatus(boolean b);

	List<MasterVehicle> findByVehicleRegistrationNoContainingAndStatus(String searchText, boolean b);

	List<MasterVehicle> findByVehicleRegistrationNoContainingAndVehicleType_vehicleTypeIdAndStatus(String searchText,
			Long searchType, boolean b);

	Boolean existsByVehicleRegistrationNo(String vehicleRegistrationNo);

	MasterVehicle findByIdAndMto_id(Long id, Long mtoId);

}