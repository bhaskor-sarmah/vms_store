package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.MasterVendorItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVendorItemRepository extends JpaRepository<MasterVendorItem, Long> {

}