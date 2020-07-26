package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.TransVendorItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVendorItemRepository extends JpaRepository<TransVendorItem, Long> {

    List<TransVendorItem> findAllByIsDeletedAndMasterVendor_idOrderByMasterItemBrand_item_itemNameAsc(boolean b,
            Long vendorId);

    List<TransVendorItem> findByMasterItemBrand_item_itemNameContaining(String searchText);

    List<TransVendorItem> findAllByMasterVendor_id(Long vendorId);

    @Modifying
    @Query("UPDATE TransVendorItem t SET t.isDeleted = 1 WHERE t.masterVendor = :masterVendor")
    int markItemAsDeletedByVendorId(@Param("masterVendor") MasterVendor masterVendor);

    List<TransVendorItem> findAllByMasterVendor_idAndIsDeleted(Long vendorId, boolean b);

}