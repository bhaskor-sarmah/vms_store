package com.bohniman.vmsmaintenance.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.MasterBrand;
import com.bohniman.vmsmaintenance.model.MasterFuelType;
import com.bohniman.vmsmaintenance.model.MasterItem;
import com.bohniman.vmsmaintenance.model.MasterItemBrand;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleCategory;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVehicleType;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.TransVendorItem;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.ScrapVehiclePayload;
import com.bohniman.vmsmaintenance.service.InventoryCategoryService;
import com.bohniman.vmsmaintenance.service.InventoryUnitService;
import com.bohniman.vmsmaintenance.service.StoreService;
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
        List<MasterFuelType> fuelTypeList = storeService.getAllFuelTypeList();
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
        MasterVehicle masterVehicle = storeService.getVehicleById(vehicleId);

        mv.addObject("masterVehicle", masterVehicle);
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
    public ResponseEntity<JsonResponse> openJobCard(@Valid @ModelAttribute TransVehicleJobCard transVehicleJobCard,
            BindingResult bindingResult) throws BindException {

        JsonResponse res = storeService.openJobCard(transVehicleJobCard);
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

        TransVehicleJobCard transVehicleJobCard = storeService.getJobCardById(jobCardId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);
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
    public ResponseEntity<JsonResponse> openJobCard(@RequestParam("jobCardId") Long jobCardId,
            @RequestParam("username") String username) throws BindException {

        JsonResponse res = storeService.forwardJobCard(jobCardId, username);
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
    // # GET SEARCHED FORWARD USER
    // ========================================================================
    @GetMapping(value = { "/vehicle/job-card/search-forward-user" })
    public ResponseEntity<JsonResponse> searchForwardUser(@RequestParam(value = "searchText") String searchText) {

        JsonResponse res = storeService.searchForwardUser(searchText);

        if (Objects.equals(res.getResult(), true)) {
            return ResponseEntity.ok().body(res);
        } else {
            throw new BadRequestException("Operation Failed");
        }
    }

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

        mv = new ModelAndView("store/job_card_home");

        List<TransVehicleJobCard> jobCards = storeService.getJobCardsByDateRange(dateFrom, dateTo);
        mv.addObject("jobCards", jobCards);

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
    // ADD NEW  ITEM
    // ========================================================================
    @PostMapping(value = { "/item/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addItem(
            @Valid @ModelAttribute MasterItem masterItem, BindingResult bindingResult)
            throws BindException {
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
    // ADD NEW  BRAND
    // ========================================================================
    @PostMapping(value = { "/brand/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addBrand(
            @Valid @ModelAttribute MasterBrand masterBrand, BindingResult bindingResult)
            throws BindException {
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
    public ResponseEntity<JsonResponse> addItemBrandVariation(
            @Valid @ModelAttribute MasterItemBrand masterItemBrand, BindingResult bindingResult)
            throws BindException {
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
    public ResponseEntity<JsonResponse> scrapVehicle(
            @Valid @ModelAttribute ScrapVehiclePayload scrapVehiclePayload, BindingResult bindingResult)
            throws BindException {
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

}