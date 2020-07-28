package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.FuelType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

}