package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterShelves;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterShelvesRepository extends JpaRepository<MasterShelves, Long> {

    Long countByMasterRack_id(Long id);

    void deleteByMasterRack_id(Long rackId);

    List<MasterShelves> findAllByMasterRack_id(Long rackId);

	List<MasterShelves> findByMasterRack_idAndIsDeletedFalse(Long rackId);

}