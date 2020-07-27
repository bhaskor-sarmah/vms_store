package com.bohniman.vmsmaintenance.repository;

import java.util.Date;
import java.util.List;

import com.bohniman.vmsmaintenance.model.TransJobCardItemOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransJobCardItemOrderRepository extends JpaRepository<TransJobCardItemOrder, Long> {

    List<TransJobCardItemOrder> findAllByTransVehicleJobCard_id(Long jobCardId);

    List<TransJobCardItemOrder> findAllByTransVehicleJobCard_idAndIsDeleted(Long jobCardId, boolean b);

	List<TransJobCardItemOrder> findByCreatedAtBetweenAndIsDeletedFalse(Date dateFrom, Date dateTo);

}