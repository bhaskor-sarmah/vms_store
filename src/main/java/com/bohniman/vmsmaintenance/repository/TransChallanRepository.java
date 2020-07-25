package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.TransChallan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransChallanRepository extends JpaRepository<TransChallan, Long> {

    List<TransChallan> findAllByOrder_id(Long orderId);

    TransChallan findByChallanNo(String challanNo);

}