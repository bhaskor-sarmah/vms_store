package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterItemRepository extends JpaRepository<MasterItem, Long>{

	List<MasterItem> findByIsDeletedOrderByItemNameAsc(boolean b);
    
}