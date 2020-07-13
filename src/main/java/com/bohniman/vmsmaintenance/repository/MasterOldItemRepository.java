package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterOldItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterOldItemRepository extends JpaRepository<MasterOldItem, Long> {

    Long countByMasterRack_id(Long id);

    void deleteByMasterRack_id(Long rackId);

    void deleteByMasterShelves_id(Long shelveId);

    Long countByMasterShelves_id(Long id);

    List<MasterOldItem> findAllByMasterShelves_id(Long shelveId);

}