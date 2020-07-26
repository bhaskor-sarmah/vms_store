package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransChallan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransChallanRepository extends JpaRepository<TransChallan, Long> {

    List<TransChallan> findAllByOrder_id(Long orderId);

    TransChallan findByChallanNo(String challanNo);

    List<TransChallan> findAllByTransBill_id(Long id);

    List<TransChallan> findAllByOrder_idAndTransBillAndIsDeleted(Long orderId, Object object, boolean b);

    List<TransChallan> findAllByTransBill_idAndIsDeleted(Long billId, boolean b);

    List<TransChallan> findAllByOrder_idAndIsDeleted(Long orderId, boolean b);

}