package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransChallan;
import com.bohniman.vmsmaintenance.model.TransItemPurchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransItemPurchaseRepository extends JpaRepository<TransItemPurchase, Long> {

    List<TransItemPurchase> findAllByOrder_idAndTransVendorItem_id(Long orderId, Long id);

    List<TransItemPurchase> findAllByTransChallan_id(Long challanId);

    @Modifying
    @Query("UPDATE TransItemPurchase t SET t.isDeleted = 1 WHERE t.transChallan = :challanId")
    int markItemAsDeletedByChallanId(@Param("challanId") TransChallan challanId);

    List<TransItemPurchase> findAllByOrder_idAndIsDeletedAndTransVendorItem_id(Long orderId, boolean b, Long id);

    List<TransItemPurchase> findAllByTransChallan_idAndIsDeleted(Long challanId, boolean b);
}