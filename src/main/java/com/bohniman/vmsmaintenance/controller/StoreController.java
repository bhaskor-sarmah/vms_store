package com.bohniman.vmsmaintenance.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.FuelType;
import com.bohniman.vmsmaintenance.model.MasterBrand;
import com.bohniman.vmsmaintenance.model.MasterItem;
import com.bohniman.vmsmaintenance.model.MasterItemBrand;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVehicleType;
import com.bohniman.vmsmaintenance.model.TransDisposeItem;
import com.bohniman.vmsmaintenance.model.TransVehicleInventory;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardForward;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItemIssue;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;
import com.bohniman.vmsmaintenance.model.TransVendorItem;
import com.bohniman.vmsmaintenance.payload.JobCardIssueItemPurchasePayload;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.ScrapVehiclePayload;
import com.bohniman.vmsmaintenance.service.InventoryCategoryService;
import com.bohniman.vmsmaintenance.service.InventoryUnitService;
import com.bohniman.vmsmaintenance.service.StoreService;
import com.bohniman.vmsmaintenance.service.StoreServiceBhaskor;
import com.bohniman.vmsmaintenance.utilities.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreServiceBhaskor storeServiceBhaskor;

    @Autowired
    StoreService storeService;

    // ========================================================================
    // STORE DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageStoreDashboard(ModelAndView mv) {
        mv = new ModelAndView("store/dashboard");

        return mv;
    }

    // TO BE DELETED
    // ========================================================================
    // VENDOR PAGE
    // ========================================================================
    // @GetMapping(value = "/vendor")
    // public ModelAndView pageVendorSearch(ModelAndView mv) {
    // mv = new ModelAndView("store/vendor_search");

    // return mv;
    // }

    // TO BE DELETED
    // ========================================================================
    // VENDOR ALL LIST
    // ========================================================================
    // @GetMapping(value = "/vendor/all")
    // @ResponseBody
    // public ResponseEntity<JsonResponse> getAllVendors() {
    // JsonResponse res = storeService.getAllVendors();
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new MyResourceNotFoundException(res.getMessage());
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // VENDOR ALL ITEMS PAGE
    // ========================================================================
    // @GetMapping(value = "/vendor/allItems/{vendorId}")
    // @ResponseBody
    // public ResponseEntity<JsonResponse>
    // getAllVendorItem(@PathVariable("vendorId") Long vendorId) {
    // JsonResponse res = storeService.getAllVendorItems(vendorId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new MyResourceNotFoundException(res.getMessage());
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // ADD NEW VENDOR
    // ========================================================================
    // @PostMapping(value = { "/vendor/add" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> addVendor(@Valid @ModelAttribute
    // MasterVendor masterVendor,
    // BindingResult bindingResult) throws BindException {
    // if (!bindingResult.hasErrors()) {
    // JsonResponse res = storeService.saveNewVendor(masterVendor);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // } else {
    // throw new BindException(bindingResult);
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // VENDOR DETAILS PAGE
    // ========================================================================
    // @GetMapping(value = { "/vendor/{vendorId}" })
    // public ModelAndView pageVendorDetail(ModelAndView mv,
    // @PathVariable("vendorId") Long vendorId) {
    // mv = new ModelAndView("store/vendor_detail");
    // mv.addObject("unitList", new InventoryUnitService().getAll());
    // mv.addObject("vendor", storeService.getVendorById(vendorId));
    // return mv;
    // }

    // TO BE DELETED
    // ========================================================================
    // DELETE VENDOR DETAILS
    // ========================================================================
    // @DeleteMapping(value = { "/vendor/delete/{vendorId}" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> deleteVendor(@PathVariable("vendorId")
    // Long vendorId) {
    // JsonResponse res = new JsonResponse();
    // res = storeService.deleteVendorById(vendorId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // ADD NEW VENDOR ITEM
    // ========================================================================
    // @PostMapping(value = { "/vendor/addItem" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> addVendorItem(@Valid @ModelAttribute
    // TransVendorItem masterVendorItem,
    // BindingResult bindingResult) throws BindException {
    // System.out.println(masterVendorItem);
    // if (!bindingResult.hasErrors()) {
    // JsonResponse res = storeService.saveNewVendorItem(masterVendorItem);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // } else {
    // throw new BindException(bindingResult);
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // DELETE VENDOR ITEM
    // ========================================================================
    // @DeleteMapping(value = { "/vendor/deleteItem/{itemId}" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> deleteVendorItem(@PathVariable("itemId")
    // Long itemId) {
    // JsonResponse res = new JsonResponse();
    // res = storeService.deleteVendorItemById(itemId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // VEHICLE DETAIL PAGE
    // ========================================================================
    // @GetMapping(value = "/vehicle/{vehicleId}")
    // public ModelAndView pageVehicleDetail(ModelAndView mv,
    // @PathVariable("vehicleId") Long vehicleId) {
    // mv = new ModelAndView("store/vehicle_detail");
    // mv.addObject("vehicleId", vehicleId);
    // return mv;
    // }

    // ========================================================================
    // ORDER DETAIL PAGE
    // ========================================================================
    @GetMapping(value = "/view-order-details/{orderId}")
    public ModelAndView pageOrderDetail(ModelAndView mv, @PathVariable("orderId") Long orderId) {
        mv = new ModelAndView("store/view_order_details");
        mv.addObject("orderId", orderId);
        return mv;
    }

    // ========================================================================
    // INVENTORY PAGE
    // ========================================================================
    @GetMapping(value = "/inventory")
    public ModelAndView pageInventory(ModelAndView mv) {
        mv = new ModelAndView("store/inventory");
        mv.addObject("categoryList", new InventoryCategoryService().getAll());
        mv.addObject("unitList", new InventoryUnitService().getAll());
        return mv;
    }

    // ========================================================================
    // ADD NEW INVENTORY ITEM
    // ========================================================================
    @PostMapping(value = { "/inventory/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addInventory(
            @Valid @ModelAttribute MasterVehicleInventory masterVehicleInventory, BindingResult bindingResult)
            throws BindException {

        // FOR NEW VEHICLE
        if (Objects.equals(masterVehicleInventory.getId(), null)) {
            Boolean itemExists = storeService.checkIfItemExistsByItemName(masterVehicleInventory.getName());
            if (itemExists) {
                bindingResult.rejectValue("name", "error.name", " * Item Already Exist.");
            }
        }
        // FOR EDIT
        else {
            MasterVehicleInventory editInventory = storeService.getVehicleInventoryById(masterVehicleInventory.getId());
            if (!Objects.equals(editInventory, null)
                    && !Objects.equals(masterVehicleInventory.getName(), editInventory.getName())) {
                Boolean itemExists = storeService.checkIfItemExistsByItemName(masterVehicleInventory.getName());
                if (itemExists) {
                    bindingResult.rejectValue("name", "error.name", " * Item Already Exist.");
                }
            }
        }

        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewInventory(masterVehicleInventory);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // LIST ALL INVENTORY
    // ========================================================================
    @GetMapping(value = { "/inventory/all" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllInventory() {

        JsonResponse res = storeService.getAllInventory();
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE INVENTORY
    // ========================================================================
    @DeleteMapping(value = { "/inventory/delete/{inventoryId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteInventory(@PathVariable("inventoryId") Long inventoryId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteInventoryById(inventoryId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // LIST ONE INVENTORY
    // ========================================================================
    @PostMapping(value = { "/getInventoryById/{inventoryId}" })
    @ResponseBody
    public MasterVehicleInventory getInventoryById(@PathVariable("inventoryId") Long inventoryId) {
        return storeService.findInventoryById(inventoryId);
    }

    // ========================================================================
    // LIST ONE VEHICLE
    // ========================================================================
    @PostMapping(value = { "/getVehicleById/{vehicleId}" })
    @ResponseBody
    public MasterVehicle getVehicleById(@PathVariable("vehicleId") Long vehicleId) {
        return storeService.findVehicleById(vehicleId);
    }

    // ========================================================================
    // LIST VEHICLE BY NUMBER AND CATEGORY
    // ========================================================================
    @PostMapping(value = { "/getVehicleByNumber" })
    @ResponseBody
    public List<MasterVehicle> getVehicleByNumber(@RequestParam("vehicleNumber") String vehicleNo,
            @RequestParam("vehicleCategory") Long category) {
        return storeService.findAllVehicleByNumber(vehicleNo, category);
    }

    // ========================================================================
    // LIST ALL VEHICLE
    // ========================================================================
    @PostMapping(value = { "/getAllVehicle" })
    @ResponseBody
    public List<MasterVehicle> getAllVehicle() {
        return storeService.findAllVehicle();
    }

    // TO BE
    // DELETED========================================================================
    // OPEN JOB CARD
    // ========================================================================
    // @GetMapping(value = "/vehicle/{vehicleId}/open-job-card")
    // public ModelAndView openJobCard(ModelAndView mv, @PathVariable("vehicleId")
    // Long vehicleId) {
    // mv = new ModelAndView("store/open_job_card");
    // mv.addObject("vehicleId", vehicleId);
    // return mv;
    // }

    // TO BE DELETED
    // ========================================================================
    // VIEW JOB CARD
    // ========================================================================
    // @GetMapping(value = "/vehicle/{vehicleId}/view-job-card")
    // public ModelAndView viewJobCard(ModelAndView mv, @PathVariable("vehicleId")
    // Long vehicleId) {
    // mv = new ModelAndView("store/view_job_card");
    // mv.addObject("vehicleId", vehicleId);
    // return mv;
    // }

    // ========================================================================
    // PAGE ORDER SHEET HOME
    // ========================================================================
    @GetMapping(value = "/order-sheet")
    public ModelAndView orderSheetHome(ModelAndView mv) {
        mv = new ModelAndView("store/order_sheet_home");
        return mv;
    }

    // ========================================================================
    // # RITUSMOI KAUSHIK
    // ========================================================================

    @GetMapping(value = "/vehicle")
    public ModelAndView pageVehicleSearch(ModelAndView mv) {
        mv = new ModelAndView("store/vehicle_search");

        List<MasterVehicleCategory> vehicleCategoryList = storeService.getAllVehicleCategoryList();
        mv.addObject("vehicleCategoryList", vehicleCategoryList);
        List<MasterVehicleType> vehicleTypeList = storeService.getAllVehicleTypeList();
        mv.addObject("vehicleTypeList", vehicleTypeList);
        List<FuelType> fuelTypeList = storeService.getAllFuelTypeList();
        mv.addObject("fuelTypeList", fuelTypeList);

        return mv;
    }

    // ========================================================================
    // ADD NEW VEHICLE
    // ========================================================================
    @PostMapping(value = { "/vehicle/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addVehicle(@Valid @ModelAttribute MasterVehicle masterVehicle,
            BindingResult bindingResult) throws BindException {

        if (Objects.equals(masterVehicle.getFuelType().getId(), null)) {
            bindingResult.rejectValue("fuelType.fuelTypeId", "error.fuelType.fuelTypeId", " * Please select fuel type");
        }
        if (Objects.equals(masterVehicle.getVehicleCategory().getVehicleCategoryId(), null)) {
            bindingResult.rejectValue("vehicleCategory.vehicleCategoryId", "error.vehicleCategory.vehicleCategoryId",
                    " * Please select category");
        }
        // FOR NEW VEHICLE
        if (Objects.equals(masterVehicle.getId(), null)) {
            Boolean vehicleExists = storeService
                    .existsByVehicleRegistrationNo(masterVehicle.getVehicleRegistrationNo());
            if (vehicleExists) {
                bindingResult.rejectValue("vehicleRegistrationNo", "error.vehicleRegistrationNo",
                        " * Vehicle Already Exists");
            }
        }
        // FOR EDIT
        else {
            MasterVehicle editVehicle = storeService.getVehicleById(masterVehicle.getId());
            if (!Objects.equals(editVehicle, null) && !Objects.equals(masterVehicle.getVehicleRegistrationNo(),
                    editVehicle.getVehicleRegistrationNo())) {
                Boolean vehicleExists = storeService
                        .existsByVehicleRegistrationNo(masterVehicle.getVehicleRegistrationNo());
                if (vehicleExists) {
                    bindingResult.rejectValue("vehicleRegistrationNo", "error.vehicleRegistrationNo",
                            " * Vehicle Already Exists");
                }
            }
        }

        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewVehicle(masterVehicle);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // SEARCH VEHICLE
    // ========================================================================
    @GetMapping(value = { "/vehicle/search" })
    @ResponseBody
    public ResponseEntity<JsonResponse> searchVehicle(@RequestParam("searchType") Long searchType,
            @RequestParam("searchText") String searchText) {
        JsonResponse res = storeService.searchVehicle(searchType, searchText);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE VEHICLE
    // ========================================================================
    @DeleteMapping(value = { "/vehicle/delete/{vehicleId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteVehicle(@PathVariable("vehicleId") Long vehicleId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteVehicleById(vehicleId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // TO BE DELETED
    // ========================================================================
    // OLD/DAMAGE CAR PARTS PAGE
    // ========================================================================
    // @GetMapping(value = "/old-car-parts")
    // public ModelAndView pageOldDamageCarParts(ModelAndView mv) {
    // mv = new ModelAndView("store/old_car_parts");
    // return mv;
    // }

    // ========================================================================
    // VEHICLE DETAIL PAGE
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}")
    public ModelAndView pageVehicleDetail(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/vehicle_detail");
        MasterVehicle masterVehicle = storeService.getVehicleById(vehicleId);
        mv.addObject("masterVehicle", masterVehicle);
        List<MasterVehicleCategory> vehicleCategoryList = storeService.getAllVehicleCategoryList();
        mv.addObject("vehicleCategoryList", vehicleCategoryList);
        List<MasterVehicleType> vehicleTypeList = storeService.getAllVehicleTypeList();
        mv.addObject("vehicleTypeList", vehicleTypeList);
        List<FuelType> fuelTypeList = storeService.getAllFuelTypeList();
        mv.addObject("fuelTypeList", fuelTypeList);
        return mv;
    }

    // TO BE DELETED
    // ========================================================================
    // LIST ALL RACK
    // ========================================================================
    // @GetMapping(value = { "/rack/all" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> getAllRacks() {

    // JsonResponse res = storeService.getAllRacks();
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new MyResourceNotFoundException(res.getMessage());
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // ADD NEW RACK
    // ========================================================================
    // @PostMapping(value = { "/rack/add" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> addRack(@Valid @ModelAttribute MasterRack
    // masterRack,
    // BindingResult bindingResult) throws BindException {
    // if (!bindingResult.hasErrors()) {
    // JsonResponse res = storeService.saveNewRack(masterRack);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // } else {
    // throw new BindException(bindingResult);
    // }
    // }

    // ========================================================================
    // OPEN JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}/open-job-card")
    public ModelAndView openJobCard(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/open_job_card");

        TransVehicleJobCard transVehicleJobCard = storeService.initiateJobCard(vehicleId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);

        List<TransVendorItem> transVendorItemList = storeService.getAllVendorItemsForJobCard();
        mv.addObject("transVendorItemList", transVendorItemList);

        return mv;
    }

    // ========================================================================
    // # GET SEARCHED VENDOR ITEM
    // ========================================================================
    @GetMapping(value = { "/get-search-vendor-item" })
    public ResponseEntity<JsonResponse> getSearchVendorItem(@RequestParam(value = "searchText") String searchText) {

        JsonResponse res = storeService.getSearchVendorItem(searchText);

        if (Objects.equals(res.getResult(), true)) {
            return ResponseEntity.ok().body(res);
        } else {
            throw new BadRequestException("Operation Failed");
        }
    }

    // TO BE DELETED
    // ========================================================================
    // DELETE A RACK
    // ========================================================================
    // @DeleteMapping(value = { "/rack/delete/{rackId}" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> deleteRack(@PathVariable("rackId") Long
    // rackId) {
    // JsonResponse res = new JsonResponse();

    // res = storeService.deleteRackById(rackId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // }

    // ========================================================================
    // OPEN JOB CARD
    // ========================================================================
    @PostMapping(value = { "/vehicle/job-card/open" })
    @ResponseBody
    public ResponseEntity<JsonResponse> openJobCard(@RequestParam("jobCardId") Long jobCardId,
            @RequestParam("vehicleId") Long vehicleId) {

        JsonResponse res = storeService.openJobCard(vehicleId, jobCardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // TO BE DELETED
    // ========================================================================
    // RACK DETAILS PAGE
    // ========================================================================
    // @GetMapping(value = { "/rack/{rackId}" })
    // public ModelAndView pageRackDetail(ModelAndView mv, @PathVariable("rackId")
    // Long rackId) {
    // mv = new ModelAndView("store/rack_detail");
    // mv.addObject("rack", storeService.getRackById(rackId));
    // return mv;
    // }

    // ========================================================================
    // VIEW JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/{jobCardId}")
    public ModelAndView viewJobCard(ModelAndView mv, @PathVariable("jobCardId") Long jobCardId) {
        mv = new ModelAndView("store/view_job_card");

        TransVehicleJobCard transVehicleJobCard = storeServiceBhaskor.getJobCardById(jobCardId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);

        if (Objects.equals(transVehicleJobCard.getStatus(), "CREATED")) {
            mv.addObject("userList", storeService.getJobCardForwarableUserList());
        }

        mv.addObject("unitList", new InventoryUnitService().getAll());

        List<MasterRack> rackList = storeService.getAllRackList();
        mv.addObject("rackList", rackList);

        return mv;
    }

    // TO BE DELETED
    // ========================================================================
    // ADD NEW SHELVES
    // ========================================================================
    // @PostMapping(value = { "/rack/addShelves" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> addShelve(@Valid @ModelAttribute
    // MasterShelves masterShelves,
    // BindingResult bindingResult) throws BindException {
    // System.out.println(masterShelves);
    // if (!bindingResult.hasErrors()) {
    // JsonResponse res = storeService.saveNewShelves(masterShelves);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // } else {
    // throw new BindException(bindingResult);
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // DELETE SHELVES RECORD
    // ========================================================================
    // @DeleteMapping(value = { "/rack/deleteShelves/{shelveId}" })
    // @ResponseBody
    // public ResponseEntity<JsonResponse> deleteShelve(@PathVariable("shelveId")
    // Long shelveId) {
    // JsonResponse res = new JsonResponse();
    // res = storeService.deleteShelveById(shelveId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new BadRequestException(res.getMessage());
    // }
    // }

    // ========================================================================
    // FORWARD JOB CARD
    // ========================================================================
    @PutMapping(value = { "/vehicle/job-card/forward" })
    @ResponseBody
    public ResponseEntity<JsonResponse> openJobCard(
            @Valid @ModelAttribute TransVehicleJobCardForward transVehicleJobCardForward, BindingResult bindingResult)
            throws BindException {

        JsonResponse res = storeService.forwardJobCard(transVehicleJobCardForward);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // TO BE DELETED
    // ========================================================================
    // ALL SHELVES LIST
    // ========================================================================
    // @GetMapping(value = "/rack/allShelves/{rackId}")
    // @ResponseBody
    // public ResponseEntity<JsonResponse> getAllShelves(@PathVariable("rackId")
    // Long rackId) {
    // JsonResponse res = storeService.getAllShelvesByRackId(rackId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new MyResourceNotFoundException(res.getMessage());
    // }
    // }

    // ========================================================================
    // # GET SEARCHED FORWARD
    // ========================================================================
    // @GetMapping(value = { "/vehicle/job-card/search-forward-user" })
    // public ResponseEntity<JsonResponse> searchForwardUser(@RequestParam(value =
    // "searchText") String searchText) {

    // JsonResponse res = storeService.searchForwardUser(searchText);

    // if (Objects.equals(res.getResult(), true)) {
    // return ResponseEntity.ok().body(res);
    // } else {
    // throw new BadRequestException("Operation Failed");
    // }
    // }

    // TO BE DELETED
    // ========================================================================
    // SHELVE DETAILS PAGE
    // ========================================================================
    // @GetMapping(value = { "/shelve/{shelveId}" })
    // public ModelAndView pageShelveDetail(ModelAndView mv,
    // @PathVariable("shelveId") Long shelveId) {
    // mv = new ModelAndView("store/shelve_detail");
    // mv.addObject("shelve", storeService.getShelveById(shelveId));
    // return mv;
    // }

    // TO BE DELETED
    // ========================================================================
    // ALL SHELVE ITEM LIST
    // ========================================================================
    // @GetMapping(value = "/shelve/allItems/{shelveId}")
    // @ResponseBody
    // public ResponseEntity<JsonResponse>
    // getAllShelveItem(@PathVariable("shelveId") Long shelveId) {
    // JsonResponse res = storeService.getAllShelveItemByShelveId(shelveId);
    // if (res.getResult()) {
    // return ResponseEntity.ok(res);
    // } else {
    // throw new MyResourceNotFoundException(res.getMessage());
    // }
    // }

    // PAGE JOB CARD HOME
    // ========================================================================
    @GetMapping(value = "/job-card")
    public ModelAndView jobCardHome(ModelAndView mv, @RequestParam Optional<String> fromDate,
            @RequestParam Optional<String> toDate) throws ParseException {
        Date dateFrom = new Date();
        Date dateTo = new Date();
        if (fromDate.isPresent() && toDate.isPresent()) {
            dateFrom = DateUtil.getDateFromString(fromDate.get());
            dateTo = DateUtil.getDateFromString(toDate.get());
        }

        Calendar calendarFrom = Calendar.getInstance();
        calendarFrom.setTime(dateFrom);
        calendarFrom.set(Calendar.HOUR_OF_DAY, 00);
        calendarFrom.set(Calendar.MINUTE, 01);

        Calendar calendarTo = Calendar.getInstance();
        calendarTo.setTime(dateTo);
        calendarTo.set(Calendar.HOUR_OF_DAY, 23);
        calendarTo.set(Calendar.MINUTE, 59);

        mv = new ModelAndView("store/job_card_home");

        List<TransVehicleJobCard> jobCards = storeService.getJobCardsByDateRange(calendarFrom.getTime(),
                calendarTo.getTime());
        mv.addObject("jobCards", jobCards);
        mv.addObject("dateFrom", dateFrom);
        mv.addObject("dateTo", dateTo);

        return mv;
    }

    // ========================================================================
    // ITEM PAGE
    // ========================================================================
    @GetMapping(value = "/item")
    public ModelAndView pageItem(ModelAndView mv) {
        mv = new ModelAndView("store/item_home");
        mv.addObject("categoryList", new InventoryCategoryService().getAll());
        mv.addObject("unitList", new InventoryUnitService().getAll());
        return mv;
    }

    // ========================================================================
    // ADD NEW ITEM
    // ========================================================================
    @PostMapping(value = { "/item/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addItem(@Valid @ModelAttribute MasterItem masterItem,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewItem(masterItem);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // LIST ALL ITEM
    // ========================================================================
    @GetMapping(value = { "/item/all" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllItem() {

        JsonResponse res = storeService.getAllItem();
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE ITEM
    // ========================================================================
    @DeleteMapping(value = { "/item/delete/{itemId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteItem(@PathVariable("itemId") Long itemId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteItemById(itemId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // BRAND PAGE
    // ========================================================================
    @GetMapping(value = "/brand")
    public ModelAndView pageBrand(ModelAndView mv) {
        mv = new ModelAndView("store/brand_home");
        return mv;
    }

    // ========================================================================
    // ADD NEW BRAND
    // ========================================================================
    @PostMapping(value = { "/brand/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addBrand(@Valid @ModelAttribute MasterBrand masterBrand,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewBrand(masterBrand);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // LIST ALL BRAND
    // ========================================================================
    @GetMapping(value = { "/brand/all" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllBrand() {

        JsonResponse res = storeService.getAllBrand();
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE BRAND
    // ========================================================================
    @DeleteMapping(value = { "/brand/delete/{brandId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteBrand(@PathVariable("brandId") Long brandId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteBrandById(brandId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // ITEM DETAIL PAGE
    // ========================================================================
    @GetMapping(value = "/item/{itemId}")
    public ModelAndView pageItemDetail(ModelAndView mv, @PathVariable("itemId") Long itemId) {
        mv = new ModelAndView("store/item_detail");
        MasterItem masterItem = storeService.getItemById(itemId);
        mv.addObject("masterItem", masterItem);

        List<MasterBrand> brandList = storeService.getAllBrandList();
        mv.addObject("brandList", brandList);

        return mv;
    }

    // ========================================================================
    // ADD NEW ITEM BRAND VARIATION
    // ========================================================================
    @PostMapping(value = { "/item/item-brand-variation/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addItemBrandVariation(@Valid @ModelAttribute MasterItemBrand masterItemBrand,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewItemBrandVariation(masterItemBrand);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // LIST ALL ITEM BRAND VARIATION
    // ========================================================================
    @GetMapping(value = { "/item/item-brand-variation/{itemId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllItemBrandVariation(@PathVariable("itemId") Long itemId) {

        JsonResponse res = storeService.getAllItemBrandVariation(itemId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE ITEM BRAND VARIATION
    // ========================================================================
    @DeleteMapping(value = { "/item/item-brand-variation/delete/{itemId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteItemBrandVariation(@PathVariable("itemId") Long itemId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteItemBrandVariationById(itemId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // SCRAP VEHICLE
    // ========================================================================
    @PutMapping(value = { "/vehicle/scrap" })
    @ResponseBody
    public ResponseEntity<JsonResponse> scrapVehicle(@Valid @ModelAttribute ScrapVehiclePayload scrapVehiclePayload,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.scrapVehicle(scrapVehiclePayload);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // ADD NEW JOB CARD ITEM
    // ========================================================================
    @PostMapping(value = { "/vehicle/job-card/add-item" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addNewJobCardItem(
            @Valid @ModelAttribute TransVehicleJobCardItems transVehicleJobCardItem, BindingResult bindingResult)
            throws BindException {

        // FOR QUANTITY LESS THAN MOQ
        TransVendorItem transVendorItem = storeService
                .getVendorItemById(transVehicleJobCardItem.getTransVendorItem().getId());
        if (Double.parseDouble(transVendorItem.getMasterItemBrand().getMoq()) > transVehicleJobCardItem.getQuantity()) {
            bindingResult.rejectValue("quantity", "error.quantity",
                    " * Please enter minimum quantity : " + transVendorItem.getMasterItemBrand().getMoq());
        }

        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.addNewJobCardItem(transVehicleJobCardItem);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // GET ALL JOB CARD ITEMS
    // ========================================================================
    @GetMapping(value = { "/vehicle/job-card/get-all-items/{jobCardId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getJobCardItemList(@PathVariable("jobCardId") Long jobCardId) {

        JsonResponse res = storeService.getJobCardItemList(jobCardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE JOB CARD ITEM
    // ========================================================================
    @DeleteMapping(value = { "/vehicle/job-card-item/delete/{itemId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteJobCardItem(@PathVariable("itemId") Long itemId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteJobCardItem(itemId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // JOB CARD VENDOR ORDER **** MOVED TO BHASKOR CONTROLLER ****
    // ========================================================================
    // @GetMapping(value = "/vehicle/job-card/{jobCardId}/vendor-order")
    // public ModelAndView jobCardVendorOrder(ModelAndView mv,
    // @PathVariable("jobCardId") Long jobCardId) {
    // mv = new ModelAndView("store/view_job_card_vendor_order");
    // mv.addObject("vendorOrderItemPayload",
    // storeService.getVendorOrderItemPayload(jobCardId));
    // TransVehicleJobCard transVehicleJobCard =
    // storeService.getJobCardById(jobCardId);
    // mv.addObject("transVehicleJobCard", transVehicleJobCard);
    // return mv;
    // }

    // ========================================================================
    // ADD STOCK TO INVENTORY
    // ========================================================================
    @PostMapping(value = { "/inventory/add-stock" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addInventoryStock(@RequestParam("id") Long id,
            @RequestParam("quantity") Double quantity) {
        JsonResponse res = storeService.addInventoryStock(id, quantity);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // # GET SEARCHED INVENTORY ITEM FOR ASSIGNING TO VEHICLE
    // ========================================================================
    @GetMapping(value = { "/inventory/search-item" })
    public ResponseEntity<JsonResponse> searchInventoryItems(@RequestParam(value = "searchText") String searchText) {

        JsonResponse res = storeService.searchInventoryItems(searchText);

        if (Objects.equals(res.getResult(), true)) {
            return ResponseEntity.ok().body(res);
        } else {
            throw new BadRequestException("Operation Failed");
        }
    }

    // ========================================================================
    // ASSIGN INVENTORY ITEM TO VEHICLE
    // ========================================================================
    @PostMapping(value = { "/inventory/vehicle/assign-item" })
    @ResponseBody
    public ResponseEntity<JsonResponse> assignInventoryItemToVehicle(
            @Valid @ModelAttribute TransVehicleInventory transVehicleInventory, BindingResult bindingResult)
            throws BindException {

        if (Objects.equals(transVehicleInventory.getVehicle_inventory(), null)
                || Objects.equals(transVehicleInventory.getVehicle_inventory().getId(), 0L)) {
            bindingResult.rejectValue("vehicle_inventory.id", "error.vehicle_inventory.id",
                    " * Please select an item.");
        }
        if (Objects.equals(transVehicleInventory.getQuantity(), null)
                || Objects.equals(transVehicleInventory.getQuantity(), 0.00)) {
            bindingResult.rejectValue("quantity", "error.quantity", " * Quantity cannot be blank or 0");
        } else {
            // CHECK IF ITEM AVAILABLE
            MasterVehicleInventory masterVehicleInventory = storeService
                    .getVehicleInventoryById(transVehicleInventory.getVehicle_inventory().getId());
            if (masterVehicleInventory.getQuantityInStore() < transVehicleInventory.getQuantity()) {
                bindingResult.rejectValue("quantity", "error.quantity",
                        " * Only " + masterVehicleInventory.getQuantityInStore() + " "
                                + masterVehicleInventory.getUnit() + " available");
            }
        }

        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.assignInventoryItemToVehicle(transVehicleInventory);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }

    }

    // ========================================================================
    // REMOVE INVENTORY ITEM TO VEHICLE
    // ========================================================================
    @PostMapping(value = { "/inventory/vehicle/remove-item" })
    @ResponseBody
    public ResponseEntity<JsonResponse> removeInventoryItemFromVehicle(
            @Valid @ModelAttribute TransVehicleInventory transVehicleInventory, BindingResult bindingResult)
            throws BindException {

        // if (Objects.equals(transVehicleInventory.getQuantity(), null)
        // || Objects.equals(transVehicleInventory.getQuantity(), 0.00)) {
        // bindingResult.rejectValue("quantity", "error.quantity", " * Quantity cannot
        // be blank or 0");
        // }

        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.removeInventoryItemFromVehicle(transVehicleInventory);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }

    }

    // ========================================================================
    // GET ALL VEHICLE ASSIGNED INVENTORY
    // ========================================================================
    @GetMapping(value = { "/inventory/vehicle/assigned-items/{vehicleId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAssignedItemList(@PathVariable("vehicleId") Long vehicleId) {

        JsonResponse res = storeService.getAssignedItemList(vehicleId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // JOB CARD ISSUE ITEM
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/{jobCardId}/issue-item")
    public ModelAndView jobCardIssueItem(ModelAndView mv, @PathVariable("jobCardId") Long jobCardId) {
        mv = new ModelAndView("store/job_card_issue_item");
        TransVehicleJobCard transVehicleJobCard = storeService.getJobCardById(jobCardId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);
        List<JobCardIssueItemPurchasePayload> purchaseList = storeService.getJobCardPurchaseListForItemIssue(jobCardId);
        mv.addObject("purchaseList", purchaseList);
        List<TransVehicleJobCardItemIssue> issuedList = storeService.getJobCardItemIssuedList(jobCardId);
        mv.addObject("issuedList", issuedList);
        return mv;
    }

    // ========================================================================
    // ISSUE ITEMS TO JOB CARD
    // ========================================================================
    @PostMapping(value = { "/vehicle/job-card/issue-item" })
    @ResponseBody
    public ResponseEntity<JsonResponse> issueItemsToJobCard(
            @Valid @ModelAttribute JobCardIssueItemPurchasePayload itemPayload, BindingResult bindingResult)
            throws BindException {

        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.issueItemsToJobCard(itemPayload);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }

    }

    // ========================================================================
    // CLOSE JOB CARD
    // ========================================================================
    @PutMapping(value = { "/vehicle/job-card-close/{jobCardId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> closeJobCard(@PathVariable("jobCardId") Long jobCardId) {
        JsonResponse res = new JsonResponse();

        res = storeService.closeJobCard(jobCardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // # GET SHELVES FOR JOB CARD DISPOSAL ITEMS
    // ========================================================================
    @GetMapping(value = { "/vehicle/job-card/get-shelf" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getShelvesForJobCardDisposal(@RequestParam("rackId") Long rackId) {

        JsonResponse res = storeService.getShelvesForJobCardDisposal(rackId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // ADD NEW DISPOSED ITEM
    // ========================================================================
    @PostMapping(value = { "/vehicle/job-card/new-dispose-item" })
    @ResponseBody
    public ResponseEntity<JsonResponse> newDisposedItem(@Valid @ModelAttribute TransDisposeItem transDisposeItem,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.newDisposedItem(transDisposeItem);
            if (res.getResult()) {
                return ResponseEntity.ok(res);
            } else {
                throw new BadRequestException(res.getMessage());
            }
        } else {
            throw new BindException(bindingResult);
        }
    }

    // ========================================================================
    // LIST ALL DISPOSED ITEM BY JOB CARD
    // ========================================================================
    @GetMapping(value = { "/vehicle/job-card/get-all-dispose-item/{jobCardId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllDisposedItemByJobCard(@PathVariable("jobCardId") Long jobCardId) {

        JsonResponse res = storeService.getAllDisposedItemByJobCard(jobCardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DELETE DISPOSED ITEM
    // ========================================================================
    @DeleteMapping(value = { "/vehicle/job-card/delete-dispose-item/{itemId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteDisposedItem(@PathVariable("itemId") Long itemId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteDisposedItem(itemId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // EDIT JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/edit-job-card/{jobCardId}")
    public ModelAndView editJobCard(ModelAndView mv, @PathVariable("jobCardId") Long jobCardId) {
        mv = new ModelAndView("store/open_job_card");

        TransVehicleJobCard transVehicleJobCard = storeServiceBhaskor.getJobCardById(jobCardId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);

        List<TransVendorItem> transVendorItemList = storeService.getAllVendorItemsForJobCard();
        mv.addObject("transVendorItemList", transVendorItemList);

        return mv;
    }

}