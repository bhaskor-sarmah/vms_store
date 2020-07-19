package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterState;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterStateRepository extends JpaRepository<MasterState, String> {

    List<MasterState> findAllByOrderByStateNameAsc();

    List<MasterState> findByStateCodeNotIn(List<String> stateCodes);

    List<MasterState> findAllByIsActiveOrderByStateNameAsc(String string);

}