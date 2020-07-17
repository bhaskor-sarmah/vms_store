package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVendorItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVendorItemRepository extends JpaRepository<TransVendorItem, Long> {

    List<TransVendorItem> findAllByMasterVendor_idOrderByItemNameAsc(Long vendorId);

    List<TransVendorItem> findByItemNameContaining(String searchText);

}