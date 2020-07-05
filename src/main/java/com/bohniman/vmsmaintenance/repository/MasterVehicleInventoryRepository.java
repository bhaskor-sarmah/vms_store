package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVehicleInventoryRepository extends JpaRepository<MasterVehicleInventory, Long> {

	List<MasterVehicleInventory> findByIsDeletedOrderByNameAsc(boolean b);

}