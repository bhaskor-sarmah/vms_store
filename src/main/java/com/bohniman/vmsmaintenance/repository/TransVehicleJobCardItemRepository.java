package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleJobCardItemRepository extends JpaRepository<TransVehicleJobCardItems, Long> {

	List<TransVehicleJobCardItems> findByTransVehicleJobCard_idAndIsDeletedFalse(Long jobCardId);

	List<TransVehicleJobCardItems> findAllByOrderIsNullAndTransVehicleJobCard_id(Long jobCardId);

	List<TransVehicleJobCardItems> findAllByOrderIsNullAndTransVehicleJobCard_idAndTransVendorItem_masterVendor_idOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(
			Long jobcardId, Long vendorId);

	List<TransVehicleJobCardItems> findAllByOrder_idOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(
			Long orderId);

	List<TransVehicleJobCardItems> findAllByOrderIsNullAndTransVehicleJobCard_idAndIsDeleted(Long jobCardId, boolean b);

	List<TransVehicleJobCardItems> findAllByOrderIsNullAndIsDeletedAndTransVehicleJobCard_idAndTransVendorItem_masterVendor_idOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(
			boolean b, Long jobcardId, Long vendorId);

	List<TransVehicleJobCardItems> findAllByOrder_idAndIsDeletedOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(
			Long orderId, boolean b);
}