package com.bohniman.vmsmaintenance.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.model.MasterFuelType;
import com.bohniman.vmsmaintenance.model.MasterMTODetails;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVehicleType;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.MasterVendorItem;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.repository.MasterFuelTypeRepository;
import com.bohniman.vmsmaintenance.repository.MasterMTODetailsRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleCategoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleInventoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleTypeRepository;
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

    public JsonResponse saveNewVendorItem(@Valid MasterVendorItem masterVendorItem) {
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

	public JsonResponse searchVehicle(Long searchType,  String searchText) {
		JsonResponse res = new JsonResponse();
        List<MasterVehicle> vehicleList = null;
        if(Objects.equals(searchType, 0L)){
             vehicleList = masterVehicleRepository.findByVehicleRegistrationNoContainingAndStatus(searchText,true);
        }
        else{
            vehicleList = masterVehicleRepository.findByVehicleRegistrationNoContainingAndVehicleType_vehicleTypeIdAndStatus(searchText,searchType,true);
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
}