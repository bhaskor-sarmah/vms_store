package com.bohniman.vmsmaintenance.repository;

import com.bohniman.vmsmaintenance.model.MasterItemBrand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterItemBrandRepository extends JpaRepository<MasterItemBrand, Long> {
    List<MasterItemBrand> findByItem_idAndIsDeleted(Long itemId, boolean b);
}