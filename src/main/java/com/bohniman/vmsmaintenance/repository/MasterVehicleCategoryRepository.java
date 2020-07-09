package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVehicleCategoryRepository extends JpaRepository<MasterVehicleCategory, Long>{

	List<MasterVehicleCategory> findByStatus(boolean b);
    
}