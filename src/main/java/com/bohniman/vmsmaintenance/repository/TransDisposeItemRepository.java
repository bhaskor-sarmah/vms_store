package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransDisposeItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransDisposeItemRepository extends JpaRepository<TransDisposeItem, Long>{

	List<TransDisposeItem> findByTransVehicleJobCard_idAndIsDeletedFalse(Long jobCardId);
    
}