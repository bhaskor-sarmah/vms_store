package com.bohniman.vmsmaintenance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.bohniman.vmsmaintenance.model.TransJobCardItemOrder;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;
import com.bohniman.vmsmaintenance.model.User;
import com.bohniman.vmsmaintenance.payload.JobCardItemPayload;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.ViewOrderItemPayload;
import com.bohniman.vmsmaintenance.repository.TransJobCardItemOrderRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardItemRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardRepository;
import com.bohniman.vmsmaintenance.utilities.LoggedInUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DcpService {

	@Autowired
	TransVehicleJobCardRepository transVehicleJobCardRepository;

	@Autowired
    TransVehicleJobCardItemRepository transVehicleJobCardItemRepository;
    
    @Autowired
    TransJobCardItemOrderRepository transJobCardItemOrderRepository;

	@Autowired
    UserService userService;

	public List<TransJobCardItemOrder> getOrderSheetsByDateRange(Date dateFrom, Date dateTo) {
		return transJobCardItemOrderRepository.findByCreatedAtBetweenAndIsDeletedFalse(dateFrom, dateTo);
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

	public JsonResponse changeOrderStatus(Long orderId, String statusType) {
		JsonResponse res = new JsonResponse();
        try {
			TransJobCardItemOrder order = transJobCardItemOrderRepository.getOne(orderId);
			if(Objects.equals(statusType, "approve")){
				order.setOrderStatus("APPROVED");
				transJobCardItemOrderRepository.save(order);
				res.setResult(true);
				res.setMessage("Order Approved Successfully.");
            }
            else if(Objects.equals(statusType, "reject")){
                order.setOrderStatus("REJECTED");
				transJobCardItemOrderRepository.save(order);
				res.setResult(true);
				res.setMessage("Order Rejected Successfully.");
            }
        } catch (Exception e) {
            res.setMessage("Order Status could not be changed.");
        }
        return res;
    }

	public JsonResponse getAllItemByOrder(Long orderId, Long vendorId, Long jobcardId) {
		JsonResponse res = new JsonResponse();
        if (orderId == 0) { // THESE ARE FRESH ITEM ORDER HAS NOT BEEN GENERATED
            List<TransVehicleJobCardItems> itemList = transVehicleJobCardItemRepository
                    .findAllByOrderIsNullAndIsDeletedAndTransVehicleJobCard_idAndTransVendorItem_masterVendor_idOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(
                            false, jobcardId, vendorId);
            List<ViewOrderItemPayload> orderItemList = new ArrayList<>();
            for (TransVehicleJobCardItems item : itemList) {
                Double pricePerUnit = item.getTransVendorItem().getPricePerUnit();
                ViewOrderItemPayload vp = new ViewOrderItemPayload();
                vp.setName(item.getTransVendorItem().getMasterItemBrand().getItem().getItemName());
                vp.setPrice(pricePerUnit * item.getQuantity());
                vp.setPricePerUnit(pricePerUnit);
                vp.setUnit(item.getTransVendorItem().getMasterItemBrand().getItem().getItemUnit());
                vp.setQuantity(item.getQuantity());
                orderItemList.add(vp);
            }
            res.setPayload(orderItemList);
        } else { // THESE ITEMS ORDER HAS BEEN GENERATED
            List<TransVehicleJobCardItems> itemList = transVehicleJobCardItemRepository
                    .findAllByOrder_idAndIsDeletedOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(orderId,
                            false);
            List<ViewOrderItemPayload> orderItemList = new ArrayList<>();
            for (TransVehicleJobCardItems item : itemList) {
                Double pricePerUnit = item.getTransVendorItem().getPricePerUnit();
                ViewOrderItemPayload vp = new ViewOrderItemPayload();
                vp.setName(item.getTransVendorItem().getMasterItemBrand().getItem().getItemName());
                vp.setPrice(pricePerUnit * item.getQuantity());
                vp.setPricePerUnit(pricePerUnit);
                vp.setUnit(item.getTransVendorItem().getMasterItemBrand().getItem().getItemUnit());
                vp.setQuantity(item.getQuantity());
                orderItemList.add(vp);
            }
            res.setPayload(orderItemList);
        }
        res.setResult(true);
        res.setMessage("Order Items fetched successfully");
        return res;
	}
    
    
}