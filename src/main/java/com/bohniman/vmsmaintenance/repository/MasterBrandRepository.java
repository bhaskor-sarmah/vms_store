package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterBrand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBrandRepository extends JpaRepository<MasterBrand, Long>{

	List<MasterBrand> findByIsDeletedOrderByBrandNameAsc(boolean b);
    
}