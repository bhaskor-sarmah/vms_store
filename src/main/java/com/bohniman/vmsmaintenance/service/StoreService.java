package com.bohniman.vmsmaintenance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.model.MasterFuelType;
import com.bohniman.vmsmaintenance.model.MasterItem;
import com.bohniman.vmsmaintenance.model.MasterItemBrand;
import com.bohniman.vmsmaintenance.model.MasterBrand;
import com.bohniman.vmsmaintenance.model.MasterMTODetails;
// import com.bohniman.vmsmaintenance.model.MasterOldItem;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVehicleType;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.TransVendorItem;
import com.bohniman.vmsmaintenance.model.TransVehicleHealth;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardForward;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;
import com.bohniman.vmsmaintenance.model.User;
import com.bohniman.vmsmaintenance.payload.JobCardItemPayload;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.MasterRackPayload;
import com.bohniman.vmsmaintenance.payload.MasterShelvePayload;
import com.bohniman.vmsmaintenance.payload.PageableObjectPayload;
import com.bohniman.vmsmaintenance.payload.ScrapVehiclePayload;
import com.bohniman.vmsmaintenance.payload.UserDataPayload;
import com.bohniman.vmsmaintenance.payload.VehiclePayload;
import com.bohniman.vmsmaintenance.repository.MasterBrandRepository;
import com.bohniman.vmsmaintenance.repository.MasterFuelTypeRepository;
import com.bohniman.vmsmaintenance.repository.MasterItemBrandRepository;
import com.bohniman.vmsmaintenance.repository.MasterBrandRepository;
import com.bohniman.vmsmaintenance.repository.MasterItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterMTODetailsRepository;
// import com.bohniman.vmsmaintenance.repository.MasterOldItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterRackRepository;
import com.bohniman.vmsmaintenance.repository.MasterShelvesRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleCategoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleInventoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleTypeRepository;
// import com.bohniman.vmsmaintenance.repository.MasterVendorItemRepository;
import com.bohniman.vmsmaintenance.repository.MasterVendorRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleHealthRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardForwardRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardItemRepository;
import com.bohniman.vmsmaintenance.repository.TransVehicleJobCardRepository;
import com.bohniman.vmsmaintenance.repository.TransVendorItemRepository;
import com.bohniman.vmsmaintenance.repository.UserRepository;
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
    TransVendorItemRepository transVendorItemRepository;

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

    // @Autowired
    // MasterOldItemRepository masterOldItemRepository;

    @Autowired
    MasterShelvesRepository masterShelvesRepository;

    @Autowired
    TransVehicleHealthRepository transVehicleHealthRepository;

    @Autowired
    TransVehicleJobCardRepository transVehicleJobCardRepository;

    @Autowired
    TransVehicleJobCardItemRepository transVehicleJobCardItemRepository;

    @Autowired
    MasterItemRepository masterItemRepository;

    @Autowired
    MasterBrandRepository masterBrandRepository;

    @Autowired
    MasterItemBrandRepository masterItemBrandRepository;

    @Autowired
    TransVehicleJobCardForwardRepository transVehicleJobCardForwardRepository;

    @Autowired
    UserRepository userRepository;

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

    // TO BE DELETED
    // ========================================================================
    // ADD NEW VENDOR
    // ========================================================================
    // public JsonResponse saveNewVendor(MasterVendor masterVendor) {
    // JsonResponse res = new JsonResponse();
    // masterVendorRepository.save(masterVendor);
    // res.setResult(true);
    // res.setMessage("Vendor Saved Successfully");
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse getAllVendors() {
    // JsonResponse res = new JsonResponse();

    // List<MasterVendor> vendorList =
    // masterVendorRepository.findAllByOrderByVendorNameAsc();

    // res.setResult(true);
    // res.setPayload(vendorList);
    // if (vendorList.isEmpty() || vendorList.size() == 0) {
    // res.setMessage("No Vendor records found.");
    // } else {
    // res.setMessage("Vendor List fetched successfully.");
    // }
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse deleteVendorById(Long vendorId) {
    // JsonResponse res = new JsonResponse();
    // try {
    // masterVendorRepository.deleteById(vendorId);
    // res.setMessage("Vendor Deleted Successfully.");
    // res.setResult(true);
    // } catch (Exception e) {
    // res.setMessage("Vendor could not be deleted.");
    // res.setResult(false);
    // }
    // return res;
    // }

    // TO BE DELETED
    // public MasterVendor getVendorById(Long vendorId) {
    // Optional<MasterVendor> vendor = masterVendorRepository.findById(vendorId);
    // if (vendor.isPresent()) {
    // return vendor.get();
    // }
    // return null;
    // }

    // TO BE DELETED
    // public JsonResponse saveNewVendorItem(TransVendorItem transVendorItem) {
    // JsonResponse res = new JsonResponse();
    // transVendorItemRepository.save(transVendorItem);
    // res.setResult(true);
    // res.setMessage("Vendor Item Saved Successfully");
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse deleteVendorItemById(Long itemId) {
    // JsonResponse res = new JsonResponse();
    // try {
    // transVendorItemRepository.deleteById(itemId);
    // res.setMessage("Vendor Item Deleted Successfully.");
    // res.setResult(true);
    // } catch (Exception e) {
    // res.setMessage("Vendor Item could not be deleted.");
    // res.setResult(false);
    // }
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse getAllVendorItems(Long vendorId) {
    // JsonResponse res = new JsonResponse();

    // List<TransVendorItem> vendorItemList = transVendorItemRepository
    // .findAllByMasterVendor_idOrderByMasterBrand_item_itemNameAsc(vendorId);

    // res.setResult(true);
    // res.setPayload(vendorItemList);
    // if (vendorItemList.isEmpty() || vendorItemList.size() == 0) {
    // res.setMessage("No Vendor Item records found.");
    // } else {
    // res.setMessage("Vendor Item List fetched successfully.");
    // }
    // return res;
    // }

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

        TransVehicleHealth transVehicleHealth = new TransVehicleHealth();
        // FOR NEW VEHICLE HEALTH
        if (Objects.equals(masterVehicle.getId(), null)) {
            transVehicleHealth.setRemarks("Vehicle has been created");
        }
        // FOR EDIT VEHICLE HEALTH
        else {
            transVehicleHealth.setRemarks("Vehicle has been edited");
        }

        MasterMTODetails mto = masterMTODetailsRepository.getOne(LoggedInUser.getLoggedInUser().getMtoId());
        masterVehicle.setMto(mto);
        masterVehicle = masterVehicleRepository.save(masterVehicle);

        transVehicleHealth.setMasterVehicle(masterVehicle);
        transVehicleHealthRepository.save(transVehicleHealth);

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
        List<VehiclePayload> vehicleDataList = new ArrayList<>();
        VehiclePayload vehiclePayload = null;

        List<MasterVehicle> vehicleList = null;
        if (Objects.equals(searchType, 0L)) {
            vehicleList = masterVehicleRepository.findByVehicleRegistrationNoContainingAndStatus(searchText, true);
        } else {
            vehicleList = masterVehicleRepository
                    .findByVehicleRegistrationNoContainingAndVehicleType_vehicleTypeIdAndStatus(searchText, searchType,
                            true);
        }

        for (MasterVehicle masterVehicle : vehicleList) {
            vehiclePayload = new VehiclePayload();
            vehiclePayload.setId(masterVehicle.getId());
            vehiclePayload.setVehicleRegistrationNo(masterVehicle.getVehicleRegistrationNo());
            vehiclePayload.setVehicleType(masterVehicle.getVehicleType());
            vehiclePayload.setVehicleCategory(masterVehicle.getVehicleCategory());
            vehiclePayload.setVehicleModel(masterVehicle.getVehicleModel());
            vehiclePayload.setFuelType(masterVehicle.getFuelType());
            vehiclePayload.setMileage(masterVehicle.getMileage());
            vehiclePayload.setScrappedReason(masterVehicle.getScrappedReason());
            vehiclePayload.setScrappedRemarks(masterVehicle.getScrappedRemarks());
            vehiclePayload.setScrappedStatus(masterVehicle.getScrappedStatus());

            if (masterVehicle.getJobCards().size() > 0) {
                for (TransVehicleJobCard transVehicleJobCard : masterVehicle.getJobCards()) {
                    if (Objects.equals(transVehicleJobCard.getStatus(), "CREATED")
                            || Objects.equals(transVehicleJobCard.getStatus(), "FORWARDED")
                            || Objects.equals(transVehicleJobCard.getStatus(), "APPROVED")) {
                        vehiclePayload.setLatestJobCardId(transVehicleJobCard.getId());
                        vehiclePayload.setLatestJobCardStatus(transVehicleJobCard.getStatus());
                    }
                }
            }

            vehicleDataList.add(vehiclePayload);
        }

        res.setResult(true);
        res.setPayload(vehicleDataList);
        res.setMessage("Vehicle List fetched successfully.");

        return res;
    }

    public Boolean existsByVehicleRegistrationNo(String vehicleRegistrationNo) {
        return masterVehicleRepository.existsByVehicleRegistrationNo(vehicleRegistrationNo);
    }

    // public MasterVehicle getVehicleById(Long id) {
    // return masterVehicleRepository.getOne(id);
    // }

    // TO BE DELETED
    // public JsonResponse getAllRacks() {
    // JsonResponse res = new JsonResponse();

    // List<MasterRack> rackList = masterRackRepository.findAll();
    // List<MasterRackPayload> payloadList = new ArrayList<>();
    // for (MasterRack m : rackList) {
    // MasterRackPayload mrp = new MasterRackPayload();
    // mrp.setId(m.getId());
    // mrp.setRackName(m.getRackName());
    // mrp.setRackDetails(m.getRackDetails());
    // mrp.setTotalItems(0L);
    // mrp.setTotalShelves(masterShelvesRepository.countByMasterRack_id(m.getId()));
    // payloadList.add(mrp);
    // }
    // res.setResult(true);
    // res.setPayload(payloadList);
    // if (payloadList.isEmpty() || payloadList.size() == 0) {
    // res.setMessage("No Rack records found.");
    // } else {
    // res.setMessage("Rack List fetched successfully.");
    // }
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse saveNewRack(MasterRack masterRack) {
    // JsonResponse res = new JsonResponse();
    // masterRackRepository.save(masterRack);
    // res.setResult(true);
    // res.setMessage("New Rack Saved Successfully");
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse deleteRackById(Long rackId) {
    // JsonResponse res = new JsonResponse();
    // try {
    // Optional<MasterRack> mro = masterRackRepository.findById(rackId);
    // if (mro.isPresent()) {
    // try {
    // // masterOldItemRepository.deleteByMasterRack_id(rackId);
    // // System.out.println("All Items of the rack is deleted");
    // masterShelvesRepository.deleteByMasterRack_id(rackId);
    // System.out.println("All Shelves of the rack is deleted");
    // masterRackRepository.deleteById(rackId);
    // res.setMessage("Rack Deleted Successfully.");
    // res.setResult(true);
    // } catch (Exception e) {
    // res.setMessage("Some error ocurred while deleteing rack !");
    // }
    // } else {
    // res.setMessage("Rack not found !");
    // }
    // } catch (Exception e) {
    // res.setMessage("Rack could not be deleted.");
    // }
    // return res;
    // }

    // TO BE DELETED
    // public MasterRack getRackById(Long rackId) {
    // Optional<MasterRack> rack = masterRackRepository.findById(rackId);
    // if (rack.isPresent()) {
    // return rack.get();
    // }
    // return null;
    // }

    // TO BE DELETED
    // public JsonResponse saveNewShelves(MasterShelves masterShelves) {
    // JsonResponse res = new JsonResponse();
    // masterShelvesRepository.save(masterShelves);
    // res.setResult(true);
    // res.setMessage("New Shelve Saved Successfully");
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse deleteShelveById(Long shelveId) {
    // JsonResponse res = new JsonResponse();
    // try {
    // Optional<MasterShelves> msl = masterShelvesRepository.findById(shelveId);
    // if (msl.isPresent()) {
    // try {
    // // masterOldItemRepository.deleteByMasterShelves_id(shelveId);
    // // System.out.println("All Items of the Shelve is deleted");
    // masterShelvesRepository.deleteById(shelveId);
    // res.setMessage("Shelve Deleted Successfully.");
    // res.setResult(true);
    // } catch (Exception e) {
    // res.setMessage("Some error ocurred while deleteing Shelve !");
    // }
    // } else {
    // res.setMessage("Shelve not found !");
    // }
    // } catch (Exception e) {
    // res.setMessage("Shelve could not be deleted.");
    // }
    // return res;
    // }

    // TO BE DELETED
    // public JsonResponse getAllShelvesByRackId(Long rackId) {
    // JsonResponse res = new JsonResponse();

    // List<MasterShelves> shelvesList =
    // masterShelvesRepository.findAllByMasterRack_id(rackId);
    // List<MasterShelvePayload> payloadList = new ArrayList<>();
    // for (MasterShelves m : shelvesList) {
    // MasterShelvePayload msf = new MasterShelvePayload();
    // msf.setId(m.getId());
    // msf.setShelveName(m.getShelveName());
    // msf.setShelveDetails(m.getShelveDetails());
    // msf.setTotalItems(0L);
    // payloadList.add(msf);
    // }
    // res.setResult(true);
    // res.setPayload(payloadList);
    // if (payloadList.isEmpty() || payloadList.size() == 0) {
    // res.setMessage("No Shelves records found.");
    // } else {
    // res.setMessage("Shelves List fetched successfully.");
    // }
    // return res;
    // }

    // TO BE DELETED
    // public MasterShelves getShelveById(Long shelveId) {
    // Optional<MasterShelves> shelve = masterShelvesRepository.findById(shelveId);
    // if (shelve.isPresent()) {
    // return shelve.get();
    // }
    // return null;
    // }

    // TO BE DELETED
    // public JsonResponse getAllShelveItemByShelveId(Long shelveId) {
    // JsonResponse res = new JsonResponse();

    // // List<MasterOldItem> shelveItemList =
    // // masterOldItemRepository.findAllByMasterShelves_id(shelveId);
    // List<String> shelveItemList = new ArrayList<>();
    // res.setResult(true);
    // res.setPayload(shelveItemList);
    // if (shelveItemList.isEmpty() || shelveItemList.size() == 0) {
    // res.setMessage("No Item records found.");
    // } else {
    // res.setMessage("Shelve Item List fetched successfully.");
    // }
    // return res;
    // }

    public MasterVehicle getVehicleById(Long id) {
        return masterVehicleRepository.findByIdAndMto_id(id, LoggedInUser.getLoggedInUser().getMtoId());
    }

    public JsonResponse getSearchVendorItem(String searchText) {
        JsonResponse res = new JsonResponse();
        PageableObjectPayload orgData = new PageableObjectPayload();

        List<TransVendorItem> itemList = transVendorItemRepository
                .findByMasterItemBrand_item_itemNameContaining(searchText);

        for (TransVendorItem item : itemList) {
            item.setMasterVendor(null);
        }

        orgData.setObjectList1(itemList);
        res.setPayload(orgData);
        res.setResult(true);

        return res;
    }

    public JsonResponse openJobCard(Long vehicleId, Long jobCardId) {
        JsonResponse res = new JsonResponse();

        TransVehicleJobCard transVehicleJobCard = transVehicleJobCardRepository.findByIdAndMasterVehicle_id(jobCardId,
                vehicleId);

        transVehicleJobCard.setStatus("CREATED");
        transVehicleJobCard = transVehicleJobCardRepository.save(transVehicleJobCard);
        res.setResult(true);
        res.setPayload(transVehicleJobCard.getId());
        res.setMessage("Job Card Opened Successfully");
        return res;
    }

    // MOVED TO BHASKOR SERVICE
    // public TransVehicleJobCard getJobCardById(Long jobCardId) {
    // return transVehicleJobCardRepository.findById(jobCardId).get();
    // }

    public JsonResponse forwardJobCard(TransVehicleJobCardForward transVehicleJobCardForward) {
        JsonResponse res = new JsonResponse();
        transVehicleJobCardForwardRepository.save(transVehicleJobCardForward);

        TransVehicleJobCard transVehicleJobCard = transVehicleJobCardRepository
                .findById(transVehicleJobCardForward.getTransVehicleJobCard().getId()).get();
        transVehicleJobCard.setStatus("FORWARDED");
        transVehicleJobCardRepository.save(transVehicleJobCard);
        res.setResult(true);
        res.setMessage("Job Card Forarded Successfully");
        return res;
    }

    // public JsonResponse searchForwardUser(String searchText) {
    // JsonResponse res = new JsonResponse();
    // PageableObjectPayload orgData = new PageableObjectPayload();
    // List<UserDataPayload> userDataPayloadList = new ArrayList<>();
    // UserDataPayload userData = null;

    // List<User> userList =
    // userRepository.findByUsernameContainingAndRoles_role(searchText,
    // "HEADMECHANIC");

    // for (User user : userList) {
    // userData = new UserDataPayload();
    // userData.setUsername(user.getUsername());
    // userData.setName(user.getName());
    // userData.setRole(user.getRoles().get(0).getRole());

    // userDataPayloadList.add(userData);
    // }

    // orgData.setObjectList1(userDataPayloadList);
    // res.setPayload(orgData);
    // res.setResult(true);

    // return res;
    // }

    public List<TransVehicleJobCard> getJobCardsByDateRange(Date dateFrom, Date dateTo) {
        return transVehicleJobCardRepository.findByCreatedAtBetween(dateFrom, dateTo);
    }

    // ========================================================================
    // # ITEM SECTION
    // ========================================================================
    public JsonResponse saveNewItem(MasterItem masterItem) {
        JsonResponse res = new JsonResponse();
        masterItemRepository.save(masterItem);
        res.setResult(true);
        res.setMessage("Item Saved Successfully");
        return res;
    }

    public JsonResponse deleteItemById(Long itemId) {
        JsonResponse res = new JsonResponse();
        try {
            MasterItem result = masterItemRepository.getOne(itemId);
            result.setIsDeleted(true);
            masterItemRepository.save(result);
            res.setResult(true);
            res.setMessage("Item Deleted Successfully.");
        } catch (Exception e) {
            res.setMessage("Item could not be deleted.");
        }
        return res;
    }

    public JsonResponse getAllItem() {
        JsonResponse res = new JsonResponse();

        List<MasterItem> itemList = masterItemRepository.findByIsDeletedOrderByItemNameAsc(false);

        res.setResult(true);
        res.setPayload(itemList);
        res.setMessage("Item List fetched successfully.");

        return res;
    }

    // ========================================================================
    // # BRAND SECTION
    // ========================================================================
    public JsonResponse saveNewBrand(MasterBrand masterBrand) {
        JsonResponse res = new JsonResponse();
        masterBrandRepository.save(masterBrand);
        res.setResult(true);
        res.setMessage("Brand Saved Successfully");
        return res;
    }

    public JsonResponse deleteBrandById(Long brandId) {
        JsonResponse res = new JsonResponse();
        try {
            MasterBrand result = masterBrandRepository.getOne(brandId);
            result.setIsDeleted(true);
            masterBrandRepository.save(result);
            res.setResult(true);
            res.setMessage("Brand Deleted Successfully.");
        } catch (Exception e) {
            res.setMessage("Brand could not be deleted.");
        }
        return res;
    }

    public JsonResponse getAllBrand() {
        JsonResponse res = new JsonResponse();

        List<MasterBrand> itemBrandList = masterBrandRepository.findByIsDeletedOrderByBrandNameAsc(false);

        res.setResult(true);
        res.setPayload(itemBrandList);
        res.setMessage("Brand List fetched successfully.");

        return res;
    }

    public MasterItem getItemById(Long itemId) {
        return masterItemRepository.findById(itemId).get();
    }

    public List<MasterBrand> getAllBrandList() {
        return masterBrandRepository.findByIsDeletedOrderByBrandNameAsc(false);
    }

    // ========================================================================
    // # ITEM BRAND SECTION
    // ========================================================================
    public JsonResponse saveNewItemBrandVariation(MasterItemBrand masterItemBrand) {
        JsonResponse res = new JsonResponse();
        masterItemBrandRepository.save(masterItemBrand);
        res.setResult(true);
        res.setMessage("Variation Saved Successfully");
        return res;
    }

    public JsonResponse deleteItemBrandVariationById(Long itemBrandId) {
        JsonResponse res = new JsonResponse();
        try {
            MasterItemBrand result = masterItemBrandRepository.getOne(itemBrandId);
            result.setIsDeleted(true);
            masterItemBrandRepository.save(result);
            res.setResult(true);
            res.setMessage("Variation Deleted Successfully.");
        } catch (Exception e) {
            res.setMessage("Variation could not be deleted.");
        }
        return res;
    }

    public JsonResponse getAllItemBrandVariation(Long itemId) {
        JsonResponse res = new JsonResponse();

        List<MasterItemBrand> itemList = masterItemBrandRepository.findByItem_idAndIsDeleted(itemId, false);

        res.setResult(true);
        res.setPayload(itemList);
        res.setMessage("Variation List fetched successfully.");

        return res;
    }

    public JsonResponse scrapVehicle(@Valid ScrapVehiclePayload scrapVehiclePayload) {
        JsonResponse res = new JsonResponse();
        try {
            MasterVehicle masterVehicle = masterVehicleRepository.getOne(scrapVehiclePayload.getVehicleId());
            masterVehicle.setScrappedReason(scrapVehiclePayload.getScrappedReason());
            masterVehicle.setScrappedRemarks(scrapVehiclePayload.getScrappedRemarks());
            masterVehicle.setScrappedStatus("SCRAPPED");
            masterVehicleRepository.save(masterVehicle);
            res.setResult(true);
            res.setMessage("Vehicle Scrapped Successfully.");
        } catch (Exception e) {
            res.setMessage("Vehicle could not be scrapped.");
        }
        return res;
    }

    public TransVehicleJobCard initiateJobCard(Long vehicleId) {
        MasterVehicle masterVehicle = masterVehicleRepository.findById(vehicleId).get();
        TransVehicleJobCard transVehicleJobCard = null;
        Boolean newJobCardRequired = true;
        if (masterVehicle.getJobCards().size() > 0) {
            for (TransVehicleJobCard tempVehicleJobCard : masterVehicle.getJobCards()) {
                if (Objects.equals(tempVehicleJobCard.getStatus(), "INITIATED")) {
                    transVehicleJobCard = tempVehicleJobCard;
                    newJobCardRequired = false;
                    break;
                }
            }
        }

        if (newJobCardRequired) {
            transVehicleJobCard = new TransVehicleJobCard();
            transVehicleJobCard.setMasterVehicle(masterVehicle);
            transVehicleJobCard.setStatus("INITIATED");
            transVehicleJobCard = transVehicleJobCardRepository.save(transVehicleJobCard);
        }

        return transVehicleJobCard;
    }

    public List<TransVendorItem> getAllVendorItemsForJobCard() {
        return transVendorItemRepository.findAll();
    }

    public JsonResponse addNewJobCardItem(@Valid TransVehicleJobCardItems transVehicleJobCardItem) {
        JsonResponse res = new JsonResponse();
        transVehicleJobCardItemRepository.save(transVehicleJobCardItem);
        res.setResult(true);
        res.setMessage("Item Added Successfully");
        return res;
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

    public JsonResponse deleteJobCardItem(Long itemId) {
        JsonResponse res = new JsonResponse();
        try {
            TransVehicleJobCardItems item = transVehicleJobCardItemRepository.getOne(itemId);
            item.setIsDeleted(true);
            transVehicleJobCardItemRepository.save(item);
            res.setResult(true);
            res.setMessage("Item Removed Successfully.");
        } catch (Exception e) {
            res.setMessage("Item could not be removed.");
        }
        return res;
    }

    public TransVendorItem getVendorItemById(Long id) {
        return transVendorItemRepository.getOne(id);
    }

    public Object getJobCardForwarableUserList() {
        return userRepository.findByRoles_role("HEADMECHANIC");
    }
}