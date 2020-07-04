package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVehicleRepository extends JpaRepository<MasterVehicle, Long> {

    List<MasterVehicle> findAllByVehicleRegistrationNo(String vehicleNo);

}