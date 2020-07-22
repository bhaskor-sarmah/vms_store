package com.bohniman.vmsmaintenance.repository;

import java.util.Date;
import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleJobCardRepository extends JpaRepository<TransVehicleJobCard, Long>{

	List<TransVehicleJobCard> findByCreatedAtBetween(Date dateFrom, Date dateTo);

	TransVehicleJobCard findByIdAndMasterVehicle_id(Long jobCardId, Long vehicleId);
    
}