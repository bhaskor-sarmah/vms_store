package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.TransVehicleHealth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleHealthRepository extends JpaRepository<TransVehicleHealth, Long>{
    
}