package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterVendorRepository extends JpaRepository<MasterVendor, Long> {

    List<MasterVendor> findAllByOrderByVendorNameAsc();

    List<MasterVendor> findAllByIsDeletedOrderByVendorNameAsc(boolean b);

}