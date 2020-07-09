package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterFuelType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterFuelTypeRepository extends JpaRepository<MasterFuelType, Long>{

	List<MasterFuelType> findByStatus(boolean b);
    
}