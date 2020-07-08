package com.bohniman.vmsmaintenance.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.MasterVendorItem;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.repository.MasterVehicleInventoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    MasterVehicleRepository masterVehicleRepository;

    @Autowired
    MasterVehicleInventoryRepository masterVehicleInventoryRepository;

    @Autowired
    MasterVendorRepository masterVendorRepository;

    @Autowired
    MasterVendorItemRepository masterVendorItemRepository;

    // ========================================================================
    // ADD NEW INVENTORY ITEM
    // ========================================================================
    public JsonResponse saveNewInventory(MasterVehicleInventory masterVehicleInventory) {
        JsonResponse res = new JsonResponse();
        masterVehicleInventoryRepository.save(masterVehicleInventory);
        res.setResult(true);
        res.setMessage("Item Saved Successfully");
        return res;
    }

    public JsonResponse deleteInventoryById(Long inventoryId) {
        JsonResponse res = new JsonResponse();
        try {
            MasterVehicleInventory result = masterVehicleInventoryRepository.getOne(inventoryId);
            result.setIsDeleted(true);
            masterVehicleInventoryRepository.deleteById(inventoryId);
            res.setResult(true);
            res.setMessage("Item Deleted Successfully.");
        } catch (Exception e) {
            res.setMessage("Item could not be deleted.");
        }
        return res;
    }

    public JsonResponse getAllInventory() {
        JsonResponse res = new JsonResponse();

        List<MasterVehicleInventory> itemList = masterVehicleInventoryRepository.findByIsDeletedOrderByNameAsc(false);

        res.setResult(true);
        res.setPayload(itemList);
        res.setMessage("Inventory List fetched successfully.");

        return res;
    }

    public MasterVehicleInventory findInventoryById(Long inventoryId) {
        return masterVehicleInventoryRepository.findById(inventoryId).get();
    }

    public boolean saveNewVehicle(MasterVehicle newVehicle) {
        return masterVehicleRepository.save(newVehicle) != null;
    }

    public List<MasterVehicle> getVehicleByNumber(String vehicleNo, Long category) {
        if (category == 0) {
            // HIRE + OWN
            return masterVehicleRepository.findAllByVehicleRegistrationNo(vehicleNo);
        }
        return null;

    }

    public boolean deleteVehicleById(Long vehicleId) {
        return false;
    }

    public MasterVehicle findVehicleById(Long vehicleId) {
        return null;
    }

    public List<MasterVehicle> findAllVehicleByNumber(String vehicleNo, Long category) {
        return null;
    }

    public List<MasterVehicle> findAllVehicle() {
        return null;
    }

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

        List<MasterVendor> vendorList = masterVendorRepository.findAllByOrderByVendorNameAsc();

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
            masterVendorRepository.deleteById(vendorId);
            res.setMessage("Vedor Deleted Successfully.");
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

    public JsonResponse saveNewVendorItem(@Valid MasterVendorItem masterVendorItem) {
        JsonResponse res = new JsonResponse();
        masterVendorItemRepository.save(masterVendorItem);
        res.setResult(true);
        res.setMessage("Vendor Item Saved Successfully");
        return res;
    }
}