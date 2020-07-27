package com.bohniman.vmsmaintenance.repository;

import java.util.Date;
import java.util.List;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransVehicleJobCardRepository extends JpaRepository<TransVehicleJobCard, Long>{

	List<TransVehicleJobCard> findByCreatedAtBetween(Date dateFrom, Date dateTo);

	TransVehicleJobCard findByIdAndMasterVehicle_id(Long jobCardId, Long vehicleId);

	List<TransVehicleJobCard> findByOpenedDateBetweenAndStatusNot(Date dateFrom, Date dateTo, String string);

	List<TransVehicleJobCard> findByOpenedDateBetweenAndStatusNotInAndForwards_forwardedToAndForwards_isCurrentTrue(
			Date dateFrom, Date dateTo, List<String> listStatusNotIn, User user);

	List<TransVehicleJobCard> findByForwards_CreatedAtBetweenAndStatusNotInAndForwards_forwardedToAndForwards_isCurrentTrue(
			Date dateFrom, Date dateTo, List<String> listStatusNotIn, User user);
    
}