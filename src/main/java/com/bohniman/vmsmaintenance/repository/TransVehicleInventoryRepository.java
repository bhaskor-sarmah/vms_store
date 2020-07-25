package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVehicleInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleInventoryRepository extends JpaRepository<TransVehicleInventory, Long>{

	List<TransVehicleInventory> findByVehicle_id(Long vehicleId);
    
}