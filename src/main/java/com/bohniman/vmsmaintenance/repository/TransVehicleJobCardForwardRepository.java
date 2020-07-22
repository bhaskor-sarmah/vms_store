package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCardForward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleJobCardForwardRepository extends JpaRepository<TransVehicleJobCardForward, Long>{
    
}