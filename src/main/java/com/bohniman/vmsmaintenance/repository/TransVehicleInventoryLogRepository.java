package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.TransVehicleInventoryLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleInventoryLogRepository extends JpaRepository<TransVehicleInventoryLog, Long>{
    
}