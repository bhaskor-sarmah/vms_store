package com.bohniman.vmsmaintenance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;
import com.bohniman.vmsmaintenance.model.User;
import com.bohniman.vmsmaintenance.payload.JobCardItemPayload;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardItemRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardRepository;
import com.bohniman.vmsmaintenance.utilities.LoggedInUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadmechanicService {

	@Autowired
	TransVehicleJobCardRepository transVehicleJobCardRepository;

	@Autowired
	TransVehicleJobCardItemRepository transVehicleJobCardItemRepository;

	@Autowired
    UserService userService;

	public List<TransVehicleJobCard> getJobCardsByDateRange(Date dateFrom, Date dateTo) {
		List<String> listStatusNotIn = new ArrayList<>();
		listStatusNotIn.add("INITIATED");
		listStatusNotIn.add("CREATED");
		User user = userService.findUserByUsername(LoggedInUser.getLoggedInUsername()).get();
		return transVehicleJobCardRepository.findByForwards_CreatedAtBetweenAndStatusNotInAndForwards_forwardedToAndForwards_isCurrentTrue(dateFrom, dateTo, listStatusNotIn, user);
	}

	public JsonResponse changeJobCardStatus(Long jobCardId, String statusType) {
		JsonResponse res = new JsonResponse();
        try {
			TransVehicleJobCard item = transVehicleJobCardRepository.getOne(jobCardId);
			if(Objects.equals(statusType, "approve")){
				item.setStatus("APPROVED");
				transVehicleJobCardRepository.save(item);
				res.setResult(true);
				res.setMessage("Job Card Approved Successfully.");
			}
        } catch (Exception e) {
            res.setMessage("Job Card Status could not be changed.");
        }
        return res;
	}

	public TransVehicleJobCard getJobCardById(Long jobCardId) {
		return transVehicleJobCardRepository.findById(jobCardId).get();
	}

	public JsonResponse getJobCardItemList(Long jobCardId) {
        JsonResponse res = new JsonResponse();
        List<JobCardItemPayload> responseList = new ArrayList<>();
        JobCardItemPayload jobCardItemPayload = null;

        List<TransVehicleJobCardItems> mainList = transVehicleJobCardItemRepository
                .findByTransVehicleJobCard_idAndIsDeletedFalse(jobCardId);

        for (TransVehicleJobCardItems item : mainList) {
            jobCardItemPayload = new JobCardItemPayload();

            jobCardItemPayload.setId(item.getId());
            jobCardItemPayload.setItemName(item.getTransVendorItem().getMasterItemBrand().getItem().getItemName());
            jobCardItemPayload.setItemUnit(item.getTransVendorItem().getMasterItemBrand().getItem().getItemUnit());
            jobCardItemPayload.setBrandName(item.getTransVendorItem().getMasterItemBrand().getBrand().getBrandName());
            jobCardItemPayload.setVendorName(item.getTransVendorItem().getMasterVendor().getVendorName());
            jobCardItemPayload.setMoq(item.getTransVendorItem().getMasterItemBrand().getMoq());
            jobCardItemPayload.setPricePerUnit(item.getTransVendorItem().getPricePerUnit());
            jobCardItemPayload.setQuantity(item.getQuantity());

            responseList.add(jobCardItemPayload);
        }

        res.setResult(true); 
        res.setPayload(responseList);
        res.setMessage("Job Card Item List fetched successfully.");

        return res;
    }
    
}