package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.MasterRack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRackRepository extends JpaRepository<MasterRack, Long> {

}