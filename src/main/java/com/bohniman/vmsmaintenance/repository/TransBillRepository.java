package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransBillRepository extends JpaRepository<TransBill, Long> {

    List<TransBill> findAllByOrder_id(Long orderId);

    List<TransBill> findAllByOrder_idAndIsDeleted(Long orderId, boolean b);

}