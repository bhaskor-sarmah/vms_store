package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransItemPurchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransItemPurchaseRepository extends JpaRepository<TransItemPurchase, Long> {

    List<TransItemPurchase> findAllByOrder_idAndTransVendorItem_id(Long orderId, Long id);

    List<TransItemPurchase> findAllByTransChallan_id(Long challanId);
	List<TransItemPurchase> findByJobCard_id(Long jobCardId);

}