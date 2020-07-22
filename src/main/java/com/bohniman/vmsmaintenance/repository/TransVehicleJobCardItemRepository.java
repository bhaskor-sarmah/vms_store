package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleJobCardItemRepository extends JpaRepository<TransVehicleJobCardItems, Long>{

	List<TransVehicleJobCardItems> findByTransVehicleJobCard_idAndIsDeletedFalse(Long jobCardId);
    
}