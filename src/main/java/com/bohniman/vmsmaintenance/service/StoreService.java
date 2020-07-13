package com.bohniman.vmsmaintenance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.model.MasterFuelType;
import com.bohniman.vmsmaintenance.model.MasterMTODetails;
import com.bohniman.vmsmaintenance.model.MasterOldItem;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVehicleType;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.MasterVendorItem;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.MasterRackPayload;
import com.bohniman.vmsmaintenance.payload.MasterShelvePayload;
import com.bohniman.vmsmaintenance.repository.MasterFuelTypeRepository;
import com.bohniman.vmsmaintenance.repository.MasterMTODetailsRepository;
import com.bohniman.vmsmaintenance.repository.MasterOldItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterRackRepository;
import com.bohniman.vmsmaintenance.repository.MasterShelvesRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleCategoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleInventoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleTypeRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorRepository;
import com.bohniman.vmsmaintenance.utilities.LoggedInUser;

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

    @Autowired
    MasterVehicleCategoryRepository masterVehicleCategoryRepository;

    @Autowired
    MasterVehicleTypeRepository masterVehicleTypeRepository;

    @Autowired
    MasterFuelTypeRepository masterFuelTypeRepository;

    @Autowired
    MasterMTODetailsRepository masterMTODetailsRepository;

    @Autowired
    MasterRackRepository masterRackRepository;

    @Autowired
    MasterOldItemRepository masterOldItemRepository;

    @Autowired
    MasterShelvesRepository masterShelvesRepository;

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
            masterVehicleInventoryRepository.save(result);
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

    public List<MasterVehicle> getVehicleByNumber(String vehicleNo, Long category) {
        if (category == 0) {
            // HIRE + OWN
            return masterVehicleRepository.findAllByVehicleRegistrationNo(vehicleNo);
        }
        return null;

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
            res.setMessage("Vendor Deleted Successfully.");
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

    public JsonResponse saveNewVendorItem(MasterVendorItem masterVendorItem) {
        JsonResponse res = new JsonResponse();
        masterVendorItemRepository.save(masterVendorItem);
        res.setResult(true);
        res.setMessage("Vendor Item Saved Successfully");
        return res;
    }

    public JsonResponse deleteVendorItemById(Long itemId) {
        JsonResponse res = new JsonResponse();
        try {
            masterVendorItemRepository.deleteById(itemId);
            res.setMessage("Vendor Item Deleted Successfully.");
            res.setResult(true);
        } catch (Exception e) {
            res.setMessage("Vendor Item could not be deleted.");
            res.setResult(false);
        }
        return res;
    }

    public JsonResponse getAllVendorItems(Long vendorId) {
        JsonResponse res = new JsonResponse();

        List<MasterVendorItem> vendorItemList = masterVendorItemRepository
                .findAllByMasterVendor_idOrderByItemNameAsc(vendorId);

        res.setResult(true);
        res.setPayload(vendorItemList);
        if (vendorItemList.isEmpty() || vendorItemList.size() == 0) {
            res.setMessage("No Vendor Item records found.");
        } else {
            res.setMessage("Vendor Item List fetched successfully.");
        }
        return res;
    }

    // ========================================================================
    // # RITUSMOI KAUSHIK
    // ========================================================================

    public List<MasterVehicleCategory> getAllVehicleCategoryList() {
        return masterVehicleCategoryRepository.findByStatus(true);
    }

    public List<MasterVehicleType> getAllVehicleTypeList() {
        return masterVehicleTypeRepository.findByStatus(true);
    }

    public List<MasterFuelType> getAllFuelTypeList() {
        return masterFuelTypeRepository.findByStatus(true);
    }

    public JsonResponse saveNewVehicle(@Valid MasterVehicle masterVehicle) {
        JsonResponse res = new JsonResponse();
        MasterMTODetails mto = masterMTODetailsRepository.getOne(LoggedInUser.getLoggedInUser().getMtoId());
        masterVehicle.setMto(mto);
        masterVehicleRepository.save(masterVehicle);
        res.setResult(true);
        res.setMessage("Vehicle Saved Successfully");
        return res;
    }

    public JsonResponse deleteVehicleById(Long vehicleId) {
        JsonResponse res = new JsonResponse();
        try {
            MasterVehicle masterVehicle = masterVehicleRepository.getOne(vehicleId);
            masterVehicle.setStatus(false);
            masterVehicleRepository.save(masterVehicle);
            res.setResult(true);
            res.setMessage("Vehicle Deleted Successfully.");
        } catch (Exception e) {
            res.setMessage("Vehicle could not be deleted.");
        }
        return res;
    }

    public JsonResponse searchVehicle(Long searchType, String searchText) {
        JsonResponse res = new JsonResponse();
        List<MasterVehicle> vehicleList = null;
        if (Objects.equals(searchType, 0L)) {
            vehicleList = masterVehicleRepository.findByVehicleRegistrationNoContainingAndStatus(searchText, true);
        } else {
            vehicleList = masterVehicleRepository
                    .findByVehicleRegistrationNoContainingAndVehicleType_vehicleTypeIdAndStatus(searchText, searchType,
                            true);
        }

        res.setResult(true);
        res.setPayload(vehicleList);
        res.setMessage("Vehicle List fetched successfully.");

        return res;
    }

    public Boolean existsByVehicleRegistrationNo(String vehicleRegistrationNo) {
        return masterVehicleRepository.existsByVehicleRegistrationNo(vehicleRegistrationNo);
    }

    public MasterVehicle getVehicleById(Long id) {
        return masterVehicleRepository.getOne(id);
    }

    public JsonResponse getAllRacks() {
        JsonResponse res = new JsonResponse();

        List<MasterRack> rackList = masterRackRepository.findAll();
        List<MasterRackPayload> payloadList = new ArrayList<>();
        for (MasterRack m : rackList) {
            MasterRackPayload mrp = new MasterRackPayload();
            mrp.setId(m.getId());
            mrp.setRackName(m.getRackName());
            mrp.setRackDetails(m.getRackDetails());
            mrp.setTotalItems(masterOldItemRepository.countByMasterRack_id(m.getId()));
            mrp.setTotalShelves(masterShelvesRepository.countByMasterRack_id(m.getId()));
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
                    masterOldItemRepository.deleteByMasterRack_id(rackId);
                    System.out.println("All Items of the rack is deleted");
                    masterShelvesRepository.deleteByMasterRack_id(rackId);
                    System.out.println("All Shelves of the rack is deleted");
                    masterRackRepository.deleteById(rackId);
                    res.setMessage("Rack Deleted Successfully.");
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
                    masterOldItemRepository.deleteByMasterShelves_id(shelveId);
                    System.out.println("All Items of the Shelve is deleted");
                    masterShelvesRepository.deleteById(shelveId);
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

        List<MasterShelves> shelvesList = masterShelvesRepository.findAllByMasterRack_id(rackId);
        List<MasterShelvePayload> payloadList = new ArrayList<>();
        for (MasterShelves m : shelvesList) {
            MasterShelvePayload msf = new MasterShelvePayload();
            msf.setId(m.getId());
            msf.setShelveName(m.getShelveName());
            msf.setShelveDetails(m.getShelveDetails());
            msf.setTotalItems(masterOldItemRepository.countByMasterShelves_id(m.getId()));
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

        List<MasterOldItem> shelveItemList = masterOldItemRepository.findAllByMasterShelves_id(shelveId);
        res.setResult(true);
        res.setPayload(shelveItemList);
        if (shelveItemList.isEmpty() || shelveItemList.size() == 0) {
            res.setMessage("No Item records found.");
        } else {
            res.setMessage("Shelve Item List fetched successfully.");
        }
        return res;
    }
}