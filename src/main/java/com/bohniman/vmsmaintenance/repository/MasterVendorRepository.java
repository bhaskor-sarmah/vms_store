package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.MasterVendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVendorRepository extends JpaRepository<MasterVendor, Long> {

}