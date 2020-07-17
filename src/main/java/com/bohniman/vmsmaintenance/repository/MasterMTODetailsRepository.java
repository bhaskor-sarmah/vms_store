package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.MasterMTODetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMTODetailsRepository extends JpaRepository<MasterMTODetails, Long> {

}