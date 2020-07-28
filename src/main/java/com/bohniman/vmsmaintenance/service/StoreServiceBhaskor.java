package com.bohniman.vmsmaintenance.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.model.MasterItemBrand;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;
import com.bohniman.vmsmaintenance.model.MasterState;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.TransBill;
import com.bohniman.vmsmaintenance.model.TransChallan;
import com.bohniman.vmsmaintenance.model.TransDisposeItem;
import com.bohniman.vmsmaintenance.model.TransItemPurchase;
import com.bohniman.vmsmaintenance.model.TransJobCardItemOrder;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;
import com.bohniman.vmsmaintenance.model.TransVendorItem;
import com.bohniman.vmsmaintenance.payload.ItemListNotInChallanPayload;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.MasterItemBrandPayload;
import com.bohniman.vmsmaintenance.payload.MasterRackPayload;
import com.bohniman.vmsmaintenance.payload.MasterShelvePayload;
import com.bohniman.vmsmaintenance.payload.NewPurchaseItemPayload;
import com.bohniman.vmsmaintenance.payload.TransItemPurchasePayload;
import com.bohniman.vmsmaintenance.payload.TransVendorItemPayload;
import com.bohniman.vmsmaintenance.payload.VendorOrderItemPayload;
import com.bohniman.vmsmaintenance.payload.ViewOrderItemPayload;
import com.bohniman.vmsmaintenance.repository.MasterItemBrandRepository;
import com.bohniman.vmsmaintenance.repository.MasterRackRepository;
import com.bohniman.vmsmaintenance.repository.MasterShelvesRepository;
import com.bohniman.vmsmaintenance.repository.MasterStateRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorRepository;
import com.bohniman.vmsmaintenance.repository.TransBillRepository;
import com.bohniman.vmsmaintenance.repository.TransChallanRepository;
import com.bohniman.vmsmaintenance.repository.TransDisposeItemRepository;
import com.bohniman.vmsmaintenance.repository.TransItemPurchaseRepository;
import com.bohniman.vmsmaintenance.repository.TransJobCardItemOrderRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardItemRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardRepository;
import com.bohniman.vmsmaintenance.repository.TransVendorItemRepository;
import com.bohniman.vmsmaintenance.utilities.AppSettings;
import com.bohniman.vmsmaintenance.utilities.OrderPdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreServiceBhaskor {

    @Autowired
    MasterVendorRepository masterVendorRepository;

    @Autowired
    TransVendorItemRepository transVendorItemRepository;

    @Autowired
    MasterRackRepository masterRackRepository;

    @Autowired
    MasterStateRepository masterStateRepository;

    @Autowired
    MasterShelvesRepository masterShelvesRepository;

    @Autowired
    MasterItemBrandRepository masterItemBrandRepository;

    @Autowired
    TransVehicleJobCardRepository transVehicleJobCardRepository;

    @Autowired
    TransVehicleJobCardItemRepository transVehicleJobCardItemRepository;

    @Autowired
    TransJobCardItemOrderRepository transJobCardItemOrderRepository;

    @Autowired
    OrderPdf orderPdf;

    @Autowired
    TransChallanRepository transChallanRepository;

    @Autowired
    TransItemPurchaseRepository transItemPurchaseRepository;

    @Autowired
    TransBillRepository transBillRepository;

    @Autowired
    TransDisposeItemRepository transDisposeItemRepository;

    // ========================================================================
    // ADD NEW VENDOR
    // ========================================================================
    public JsonResponse saveNewVendor(MasterVendor masterVendor) {
        JsonResponse res = new JsonResponse();
        masterVendorRepository.save(masterVendor);
        res.setResult(true);
        res.setMessage("Vendor Saved Successfully");
        return res;
    }

    public JsonResponse getAllVendors() {
        JsonResponse res = new JsonResponse();

        List<MasterVendor> vendorList = masterVendorRepository.findAllByIsDeletedOrderByVendorNameAsc(false);

        res.setResult(true);
        res.setPayload(vendorList);
        if (vendorList.isEmpty() || vendorList.size() == 0) {
            res.setMessage("No Vendor records found.");
        } else {
            res.setMessage("Vendor List fetched successfully.");
        }
        return res;
    }

    public JsonResponse deleteVendorById(Long vendorId) {
        JsonResponse res = new JsonResponse();
        try {
            // masterVendorRepository.deleteById(vendorId);
            Optional<MasterVendor> mro = masterVendorRepository.findById(vendorId);
            if (mro.isPresent()) {
                MasterVendor vendor = mro.get();
                vendor.setIsDeleted(true);
                masterVendorRepository.save(vendor);
                transVendorItemRepository.markItemAsDeletedByVendorId(vendor);
                res.setMessage("Vendor deleted successfully.");
            } else {
                res.setMessage("Vendor not found.");
            }
            res.setResult(true);
        } catch (Exception e) {
            res.setMessage("Vendor could not be deleted.");
            res.setResult(false);
        }
        return res;
    }

    public MasterVendor getVendorById(Long vendorId) {
        Optional<MasterVendor> vendor = masterVendorRepository.findById(vendorId);
        if (vendor.isPresent()) {
            return vendor.get();
        }
        return null;
    }

    public JsonResponse saveNewVendorItem(TransVendorItem transVendorItem) {
        JsonResponse res = new JsonResponse();
        transVendorItemRepository.save(transVendorItem);
        res.setResult(true);
        res.setMessage("Vendor Item Saved Successfully");
        return res;
    }

    public JsonResponse deleteVendorItemById(Long itemId) {
        JsonResponse res = new JsonResponse();
        try {
            Optional<TransVendorItem> mro = transVendorItemRepository.findById(itemId);
            if (mro.isPresent()) {
                TransVendorItem vendorItem = mro.get();
                vendorItem.setIsDeleted(true);
                transVendorItemRepository.save(vendorItem);
                res.setMessage("Vendor Item Deleted Successfully.");
            } else {
                res.setMessage("Vendor Item not found.");
            }
            res.setResult(true);
        } catch (Exception e) {
            res.setMessage("Vendor Item could not be deleted.");
            res.setResult(false);
        }
        return res;
    }

    public JsonResponse getAllVendorItems(Long vendorId) {
        JsonResponse res = new JsonResponse();
        List<TransVendorItem> vendorItemList = transVendorItemRepository
                .findAllByIsDeletedAndMasterVendor_idOrderByMasterItemBrand_item_itemNameAsc(false, vendorId);
        List<TransVendorItemPayload> transVendorItems = new ArrayList<>();
        for (TransVendorItem item : vendorItemList) {
            TransVendorItemPayload tp = new TransVendorItemPayload();
            tp.setId(item.getId());
            tp.setItemBrand(item.getMasterItemBrand().getBrand().getBrandName());
            tp.setItemName(item.getMasterItemBrand().getItem().getItemName());
            tp.setUnit(item.getMasterItemBrand().getItem().getItemUnit());
            tp.setMoq(item.getMasterItemBrand().getMoq());
            tp.setItemPrice(item.getPricePerUnit());
            transVendorItems.add(tp);
        }
        res.setResult(true);
        res.setPayload(transVendorItems);
        if (transVendorItems.isEmpty() || transVendorItems.size() == 0) {
            res.setMessage("No Vendor Item records found.");
        } else {
            res.setMessage("Vendor Item List fetched successfully.");
        }
        return res;
    }

    public JsonResponse getAllRacks() {
        JsonResponse res = new JsonResponse();

        List<MasterRack> rackList = masterRackRepository.findAllByIsDeleted(false);
        List<MasterRackPayload> payloadList = new ArrayList<>();
        for (MasterRack m : rackList) {
            MasterRackPayload mrp = new MasterRackPayload();
            mrp.setId(m.getId());
            mrp.setRackName(m.getRackName());
            mrp.setRackDetails(m.getRackDetails());
            mrp.setTotalItems(0L);
            mrp.setTotalShelves(masterShelvesRepository.countByMasterRack_idAndIsDeleted(m.getId(), false));
            payloadList.add(mrp);
        }
        res.setResult(true);
        res.setPayload(payloadList);
        if (payloadList.isEmpty() || payloadList.size() == 0) {
            res.setMessage("No Rack records found.");
        } else {
            res.setMessage("Rack List fetched successfully.");
        }
        return res;
    }

    public JsonResponse saveNewRack(MasterRack masterRack) {
        JsonResponse res = new JsonResponse();
        masterRackRepository.save(masterRack);
        res.setResult(true);
        res.setMessage("New Rack Saved Successfully");
        return res;
    }

    public JsonResponse deleteRackById(Long rackId) {
        JsonResponse res = new JsonResponse();
        try {
            Optional<MasterRack> mro = masterRackRepository.findById(rackId);
            if (mro.isPresent()) {
                try {

                    MasterRack rack = mro.get();
                    rack.setIsDeleted(true);
                    masterRackRepository.save(rack);
                    res.setMessage("Rack Deleted Successfully.");

                    masterShelvesRepository.markShelveAsDeletedByRackId(rack);
                    System.out.println("All Shelves of the rack is deleted");

                    res.setResult(true);
                } catch (Exception e) {
                    res.setMessage("Some error ocurred while deleteing rack !");
                }
            } else {
                res.setMessage("Rack not found !");
            }
        } catch (Exception e) {
            res.setMessage("Rack could not be deleted.");
        }
        return res;
    }

    public MasterRack getRackById(Long rackId) {
        Optional<MasterRack> rack = masterRackRepository.findById(rackId);
        if (rack.isPresent()) {
            return rack.get();
        }
        return null;
    }

    public JsonResponse saveNewShelves(MasterShelves masterShelves) {
        JsonResponse res = new JsonResponse();
        masterShelvesRepository.save(masterShelves);
        res.setResult(true);
        res.setMessage("New Shelve Saved Successfully");
        return res;
    }

    public JsonResponse deleteShelveById(Long shelveId) {
        JsonResponse res = new JsonResponse();
        try {
            Optional<MasterShelves> msl = masterShelvesRepository.findById(shelveId);
            if (msl.isPresent()) {
                try {
                    MasterShelves shelve = msl.get();
                    shelve.setIsDeleted(true);
                    masterShelvesRepository.save(shelve);
                    res.setMessage("Shelve Deleted Successfully.");
                    res.setResult(true);
                } catch (Exception e) {
                    res.setMessage("Some error ocurred while deleteing Shelve !");
                }
            } else {
                res.setMessage("Shelve not found !");
            }
        } catch (Exception e) {
            res.setMessage("Shelve could not be deleted.");
        }
        return res;
    }

    public JsonResponse getAllShelvesByRackId(Long rackId) {
        JsonResponse res = new JsonResponse();

        List<MasterShelves> shelvesList = masterShelvesRepository.findAllByMasterRack_idAndIsDeleted(rackId, false);
        List<MasterShelvePayload> payloadList = new ArrayList<>();
        for (MasterShelves m : shelvesList) {
            MasterShelvePayload msf = new MasterShelvePayload();
            msf.setId(m.getId());
            msf.setShelveName(m.getShelveName());
            msf.setShelveDetails(m.getShelveDetails());
            msf.setTotalItems(transDisposeItemRepository.countByMasterShelve_idAndIsDeleted(m.getId(), false));
            payloadList.add(msf);
        }
        res.setResult(true);
        res.setPayload(payloadList);
        if (payloadList.isEmpty() || payloadList.size() == 0) {
            res.setMessage("No Shelves records found.");
        } else {
            res.setMessage("Shelves List fetched successfully.");
        }
        return res;
    }

    public MasterShelves getShelveById(Long shelveId) {
        Optional<MasterShelves> shelve = masterShelvesRepository.findById(shelveId);
        if (shelve.isPresent()) {
            return shelve.get();
        }
        return null;
    }

    public JsonResponse getAllShelveItemByShelveId(Long shelveId) {
        JsonResponse res = new JsonResponse();
        List<TransDisposeItem> shelveItemList = transDisposeItemRepository
                .findAllByMasterShelve_idAndIsDeleted(shelveId, false);
        for (TransDisposeItem item : shelveItemList) {
            item.getTransVehicleJobCard().setForwards(null);
            item.getTransVehicleJobCard().setItems(null);
            item.getTransVehicleJobCard().setMasterVehicle(null);
        }
        res.setResult(true);
        res.setPayload(shelveItemList);
        if (shelveItemList.isEmpty() || shelveItemList.size() == 0) {
            res.setMessage("No Item records found.");
        } else {
            res.setMessage("Shelve Item List fetched successfully.");
        }
        return res;
    }

    public List<MasterState> getAllStates() {
        return masterStateRepository.findAllByIsActiveOrderByStateNameAsc("1");
    }

    public List<MasterItemBrandPayload> getItemByVendorId(Long vendorId) {
        List<MasterItemBrand> itemlist = masterItemBrandRepository.findAll();
        List<TransVendorItem> vendorItemList = transVendorItemRepository.findAllByMasterVendor_idAndIsDeleted(vendorId,
                false);
        List<MasterItemBrandPayload> itemlistFiltered = new ArrayList<>();
        for (MasterItemBrand masterItemBrand : itemlist) {
            boolean found = false;
            for (TransVendorItem transVendorItem : vendorItemList) {
                if (masterItemBrand.getId() == transVendorItem.getMasterItemBrand().getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                MasterItemBrandPayload m = new MasterItemBrandPayload();
                m.setId(masterItemBrand.getId());
                m.setItemBrand(masterItemBrand.getBrand().getBrandName());
                m.setItemName(masterItemBrand.getItem().getItemName());
                m.setUnit(masterItemBrand.getItem().getItemUnit());
                m.setMoq(masterItemBrand.getMoq());
                itemlistFiltered.add(m);
            }
        }
        return itemlistFiltered;
    }

    public JsonResponse saveVendorItemPrice(Long itemId, Long vendorId, Double price) {
        TransVendorItem tv = new TransVendorItem();
        tv.setCurrentStatus(AppSettings.VENDOR_ITEM_STATUS_ACTIVE);
        tv.setIsApprovedItem(false);
        tv.setMasterItemBrand(masterItemBrandRepository.findById(itemId).get());
        tv.setPricePerUnit(price);
        tv.setMasterVendor(masterVendorRepository.findById(vendorId).get());

        JsonResponse res = new JsonResponse();
        transVendorItemRepository.save(tv);
        res.setResult(true);
        res.setMessage("Vendor Item Price saved successfully");
        return res;
    }

    public JsonResponse updateVendorItem(Long itemId, Double itemPrice) {
        JsonResponse res = new JsonResponse();
        TransVendorItem tv = transVendorItemRepository.findById(itemId).get();
        tv.setPricePerUnit(itemPrice);
        transVendorItemRepository.save(tv);
        res.setResult(true);
        res.setMessage("Vendor Item Price updated successfully");
        return res;
    }

    // MOVED FROM RITUSMOI SERVICE
    public TransVehicleJobCard getJobCardById(Long jobCardId) {
        return transVehicleJobCardRepository.findById(jobCardId).get();
    }

    public List<VendorOrderItemPayload> getVendorOrderItemPayload(Long jobCardId) {
        List<VendorOrderItemPayload> vendorOrderItemList = new ArrayList<>();

        // GETTING ALL ITEMS GROUP BY VENDOR FOR WHICH NO ORDER IS FOUND
        List<TransVehicleJobCardItems> itemList = transVehicleJobCardItemRepository
                .findAllByOrderIsNullAndTransVehicleJobCard_idAndIsDeleted(jobCardId, false);
        for (TransVehicleJobCardItems item : itemList) {
            boolean vendorFound = false;
            for (VendorOrderItemPayload v : vendorOrderItemList) {
                if (v.getVendorId() == item.getTransVendorItem().getMasterVendor().getId()) {
                    vendorFound = true;
                    v.setAmount(v.getAmount() + (item.getQuantity() * item.getTransVendorItem().getPricePerUnit()));
                    break;
                }
            }
            if (!vendorFound) {
                VendorOrderItemPayload vp = new VendorOrderItemPayload();
                vp.setVendorId(item.getTransVendorItem().getMasterVendor().getId());
                vp.setVendorName(item.getTransVendorItem().getMasterVendor().getVendorName());
                vp.setAmount(item.getQuantity() * item.getTransVendorItem().getPricePerUnit());
                vp.setJobCardId(jobCardId);
                vp.setStatus(AppSettings.ORDER_STATUS_FRESH);
                vendorOrderItemList.add(vp);
            }
        }

        // GETTING ALL ITEMS GROUP BY VENDOR FOR WHICH ORDER HAS BEEN PLACED
        List<TransJobCardItemOrder> itemOrderList = transJobCardItemOrderRepository
                .findAllByTransVehicleJobCard_idAndIsDeleted(jobCardId, false);
        for (TransJobCardItemOrder order : itemOrderList) {
            VendorOrderItemPayload vp = new VendorOrderItemPayload();
            vp.setVendorId(order.getVendor().getId());
            vp.setVendorName(order.getVendor().getVendorName());
            vp.setAmount(order.getTotalAmount());
            vp.setJobCardId(jobCardId);
            vp.setStatus(order.getOrderStatus());
            vp.setOrderId(order.getId());
            vendorOrderItemList.add(vp);
        }
        return vendorOrderItemList;
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

    public JsonResponse generateOrder(Long vendorId, Long jobCardId) {
        JsonResponse res = new JsonResponse();
        List<TransVehicleJobCardItems> itemList = transVehicleJobCardItemRepository
                .findAllByOrderIsNullAndIsDeletedAndTransVehicleJobCard_idAndTransVendorItem_masterVendor_idOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(
                        false, jobCardId, vendorId);
        Double totalItemPrice = 0D;
        for (TransVehicleJobCardItems item : itemList) {
            totalItemPrice += item.getTransVendorItem().getPricePerUnit() * item.getQuantity();
        }
        TransJobCardItemOrder jobCardOrder = new TransJobCardItemOrder();
        jobCardOrder.setOrderStatus(AppSettings.ORDER_STATUS_PENDING);
        jobCardOrder.setTotalAmount(totalItemPrice);
        jobCardOrder.setVendor(masterVendorRepository.findById(vendorId).get());
        jobCardOrder.setTransVehicleJobCard(transVehicleJobCardRepository.findById(jobCardId).get());
        jobCardOrder = transJobCardItemOrderRepository.save(jobCardOrder);
        for (TransVehicleJobCardItems item : itemList) {
            item.setOrder(jobCardOrder);
            transVehicleJobCardItemRepository.save(item);
        }
        res.setPayload(jobCardOrder.getId());
        res.setResult(true);
        res.setMessage("Order Generated and Sent for Approval Successfully !");
        return res;
    }

    public JsonResponse downloadOrder(Long orderId) {
        JsonResponse res = new JsonResponse();
        TransJobCardItemOrder order = transJobCardItemOrderRepository.findById(orderId).get();
        order.setOrderStatus(AppSettings.ORDER_STATUS_PLACED);
        transJobCardItemOrderRepository.save(order);
        // res.setPayload(jobCardOrder.getId());
        res.setResult(true);
        res.setMessage("Order Generated and Sent for Approval Successfully !");
        return res;
    }

    public ByteArrayInputStream generateOrderPdf(Long orderId) {
        if (orderId == 0L) {
            orderPdf.generateErrorPdf();
        }
        return orderPdf.generateOrderPdf(transJobCardItemOrderRepository.findById(orderId).get());
    }

    public TransJobCardItemOrder getOrderById(Long orderId) {
        return transJobCardItemOrderRepository.findById(orderId).get();
    }

    public JsonResponse getAllChallanByOrder(Long orderId) {
        JsonResponse res = new JsonResponse();
        List<TransChallan> challanList = transChallanRepository.findAllByOrder_idAndIsDeleted(orderId, false);
        for (TransChallan challan : challanList) {
            challan.setOrder(null);
            if (challan.getTransBill() == null) {
                challan.setTransBill(new TransBill(""));
            } else {
                challan.getTransBill().setOrder(null);
            }
        }
        res.setPayload(challanList);
        if (challanList.isEmpty() || challanList.size() == 0) {
            res.setMessage("No Challan Entry Found.");
        } else {
            res.setMessage("All Challans fetched successfully.");
        }
        res.setResult(true);
        return res;
    }

    public List<ItemListNotInChallanPayload> getItemListNotInChallanByOrderId(Long orderId) {

        List<ItemListNotInChallanPayload> itemList = new ArrayList<>();

        List<TransVehicleJobCardItems> itemListByOrder = transVehicleJobCardItemRepository
                .findAllByOrder_idAndIsDeletedOrderByTransVendorItem_masterItemBrand_item_itemNameDesc(orderId, false);

        for (TransVehicleJobCardItems item : itemListByOrder) {
            ItemListNotInChallanPayload ip = new ItemListNotInChallanPayload();
            ip.setTransVendorItemId(item.getTransVendorItem().getId());
            ip.setItemName(item.getTransVendorItem().getMasterItemBrand().getItem().getItemName());
            ip.setItemUnit(item.getTransVendorItem().getMasterItemBrand().getItem().getItemUnit());
            ip.setOrderQuantity(item.getQuantity());
            List<TransItemPurchase> purchaseList = transItemPurchaseRepository
                    .findAllByOrder_idAndIsDeletedAndTransVendorItem_id(orderId, false,
                            item.getTransVendorItem().getId());
            Double totalInChallan = 0D;
            for (TransItemPurchase purchase : purchaseList) {
                totalInChallan += purchase.getPuchaseQuantity();
            }
            ip.setQuantityRemainingToReceive(item.getQuantity() - totalInChallan);
            if (item.getQuantity() - totalInChallan > 0) {
                itemList.add(ip);
            }
        }
        return itemList;
    }

    public JsonResponse saveChallan(@Valid TransChallan transChallan, Long jobCardId,
            List<NewPurchaseItemPayload> purchaseItem) {
        JsonResponse res = new JsonResponse();
        try {
            Double noOfItems = 0D;
            for (NewPurchaseItemPayload newPurchaseItem : purchaseItem) {
                noOfItems += newPurchaseItem.getNoOfItem();
            }
            transChallan.setNoOfItems(noOfItems);
            transChallan.setTotalQuantity(Long.valueOf(purchaseItem.size()));
            transChallan = transChallanRepository.save(transChallan);

            TransVehicleJobCard jobCard = transVehicleJobCardRepository.findById(jobCardId).get();
            TransJobCardItemOrder order = transChallan.getOrder();
            for (NewPurchaseItemPayload newPurchaseItem : purchaseItem) {
                TransItemPurchase itemPurchase = new TransItemPurchase();
                itemPurchase.setItemStatus(AppSettings.PURCHASE_ITEM_STATUS_FRESH);
                itemPurchase.setItemType(AppSettings.PURCHASE_ITEM_TYPE_NEW);
                itemPurchase.setPurchaseDate(transChallan.getChallanDate());
                itemPurchase.setPuchaseQuantity(newPurchaseItem.getNoOfItem());
                itemPurchase.setWarrantyUpto(newPurchaseItem.getWarrantyUpto());

                itemPurchase.setJobCard(jobCard);
                itemPurchase.setTransVendorItem(
                        transVendorItemRepository.findById(newPurchaseItem.getTransVendorItemId()).get());
                itemPurchase.setTransChallan(transChallan);
                itemPurchase.setOrder(order);

                transItemPurchaseRepository.save(itemPurchase);
            }
            res.setMessage("New Challan Saved Successfully");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            res.setMessage("Error saving new Challan");
        }
        res.setResult(true);
        return res;
    }

    public boolean checkIfChallanExist(String challanNo) {
        return transChallanRepository.findByChallanNo(challanNo) != null;
    }

    public JsonResponse getAllItemByChallan(Long challanId) {
        JsonResponse res = new JsonResponse();
        List<TransItemPurchase> purchaseItem = transItemPurchaseRepository
                .findAllByTransChallan_idAndIsDeleted(challanId, false);
        List<TransItemPurchasePayload> purchaseItemPayload = new ArrayList<>();
        for (TransItemPurchase purchase : purchaseItem) {
            TransItemPurchasePayload tp = new TransItemPurchasePayload();
            tp.setName(purchase.getTransVendorItem().getMasterItemBrand().getItem().getItemName());
            tp.setUnit(purchase.getTransVendorItem().getMasterItemBrand().getItem().getItemUnit());
            tp.setQuantity(purchase.getPuchaseQuantity());
            tp.setWarranty((purchase.getWarrantyUpto() == null) ? "" : purchase.getWarrantyUpto().toString());
            purchaseItemPayload.add(tp);
        }
        res.setPayload(purchaseItemPayload);
        res.setResult(true);
        res.setMessage("Order Items fetched successfully");
        return res;
    }

    @Transactional
    public JsonResponse deleteChallanById(Long challanId) {
        JsonResponse res = new JsonResponse();
        try {
            Optional<TransChallan> msl = transChallanRepository.findById(challanId);
            if (msl.isPresent()) {
                try {
                    TransChallan challan = msl.get();
                    if (deleteChallan(challan)) {
                        res.setMessage("Challan Deleted Successfully.");
                    } else {
                        res.setMessage("Some error ocurred while deleteing Challan !");
                    }

                    res.setResult(true);
                } catch (Exception e) {
                    res.setMessage("Some error ocurred while deleteing Challan !");
                }
            } else {
                res.setMessage("Challan not found !");
            }
        } catch (Exception e) {
            res.setMessage("Challan could not be deleted.");
        }
        return res;
    }

    public JsonResponse getAllBillByOrder(Long orderId) {
        JsonResponse res = new JsonResponse();
        List<TransBill> billList = transBillRepository.findAllByOrder_idAndIsDeleted(orderId, false);
        for (TransBill bill : billList) {
            bill.setOrder(null);
        }
        res.setPayload(billList);
        if (billList.isEmpty() || billList.size() == 0) {
            res.setMessage("No Bill Entry Found.");
        } else {
            res.setMessage("All Bills fetched successfully.");
        }
        res.setResult(true);
        return res;
    }

    public JsonResponse deleteBillById(Long billId) {
        JsonResponse res = new JsonResponse();
        try {
            Optional<TransBill> msl = transBillRepository.findById(billId);
            if (msl.isPresent()) {
                try {
                    TransBill bill = msl.get();
                    bill.setIsDeleted(true);
                    bill = transBillRepository.save(bill);

                    List<TransChallan> challanList = transChallanRepository.findAllByTransBill_id(bill.getId());
                    for (TransChallan challan : challanList) {
                        challan.setTransBill(null);
                        transChallanRepository.save(challan);
                    }
                    res.setMessage("Bill Deleted Successfully.");

                    res.setResult(true);
                } catch (Exception e) {
                    res.setMessage("Some error ocurred while deleteing Bill !");
                }
            } else {
                res.setMessage("Bill not found !");
            }
        } catch (Exception e) {
            res.setMessage("Bill could not be deleted.");
        }
        return res;
    }

    @Transactional
    public boolean deleteChallan(TransChallan challan) {
        boolean res = false;
        try {
            challan.setIsDeleted(true);
            challan = transChallanRepository.save(challan);
            transItemPurchaseRepository.markItemAsDeletedByChallanId(challan);
            return true;
        } catch (Exception e) {
        }
        return res;
    }

    public JsonResponse getAllChallanNotInBillByOrder(Long orderId) {
        JsonResponse res = new JsonResponse();
        List<TransChallan> challanList = transChallanRepository.findAllByOrder_idAndTransBillAndIsDeleted(orderId, null,
                false);
        for (TransChallan challan : challanList) {
            challan.setOrder(null);
        }
        res.setPayload(challanList);
        if (challanList.isEmpty() || challanList.size() == 0) {
            res.setMessage("No Challan Entry Found.");
        } else {
            res.setMessage("All Challans fetched successfully.");
        }
        res.setResult(true);
        return res;
    }

    public JsonResponse saveNewBill(@Valid TransBill transBill, List<Long> challanIds) {
        JsonResponse res = new JsonResponse();
        transBill.setStatus(AppSettings.TRANS_BILL_STATUS_RECEIVED);
        transBill = transBillRepository.save(transBill);
        for (Long id : challanIds) {
            TransChallan challan = transChallanRepository.findById(id).get();
            challan.setTransBill(transBill);
            transChallanRepository.save(challan);
        }
        res.setResult(true);
        res.setMessage("New Shelve Saved Successfully");
        return res;
    }

    public JsonResponse getAllChallanByBill(Long billId) {
        JsonResponse res = new JsonResponse();
        List<TransChallan> challanList = transChallanRepository.findAllByTransBill_idAndIsDeleted(billId, false);
        for (TransChallan challan : challanList) {
            challan.setOrder(null);
            challan.getTransBill().setOrder(null);
        }
        res.setPayload(challanList);
        if (challanList.isEmpty() || challanList.size() == 0) {
            res.setMessage("No Challan Entry Found.");
        } else {
            res.setMessage("All Challans fetched successfully.");
        }
        res.setResult(true);
        return res;
    }

    public void closeOrder(Long orderId) {
        Optional<TransJobCardItemOrder> orderOption = transJobCardItemOrderRepository.findById(orderId);
        if (orderOption.isPresent()) {
            TransJobCardItemOrder order = orderOption.get();
            order.setOrderStatus(AppSettings.ORDER_STATUS_CLOSED);
            transJobCardItemOrderRepository.save(order);
        }
    }
}