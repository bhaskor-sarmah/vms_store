package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVehicleInventoryRepository extends JpaRepository<MasterVehicleInventory, Long> {

}