package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicleType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVehicleTypeRepository extends JpaRepository<MasterVehicleType, Long>{

	List<MasterVehicleType> findByStatus(boolean b);
    
}