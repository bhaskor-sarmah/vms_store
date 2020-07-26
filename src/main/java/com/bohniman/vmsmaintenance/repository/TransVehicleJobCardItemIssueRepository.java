package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItemIssue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleJobCardItemIssueRepository extends JpaRepository<TransVehicleJobCardItemIssue, Long>{

	List<TransVehicleJobCardItemIssue> findByTransVehicleJobCard_idAndIsDeletedFalse(Long jobCardId);
    
}