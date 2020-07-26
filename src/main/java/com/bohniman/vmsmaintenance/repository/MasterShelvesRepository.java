package com.bohniman.vmsmaintenance.repository;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterShelvesRepository extends JpaRepository<MasterShelves, Long> {

    Long countByMasterRack_id(Long id);

    void deleteByMasterRack_id(Long rackId);

    List<MasterShelves> findAllByMasterRack_id(Long rackId);

    Long countByMasterRack_idAndIsDeleted(Long id, boolean b);

    List<MasterShelves> findAllByMasterRack_idAndIsDeleted(Long rackId, boolean b);

    @Modifying
    @Query("UPDATE MasterShelves t SET t.isDeleted = 1 WHERE t.masterRack = :masterRack")
    int markShelveAsDeletedByRackId(@Param("masterRack") MasterRack masterRack);

}