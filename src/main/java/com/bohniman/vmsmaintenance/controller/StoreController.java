package com.bohniman.vmsmaintenance.controller;

import java.util.List;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.MasterVendorItem;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.service.InventoryCategoryService;
import com.bohniman.vmsmaintenance.service.InventoryUnitService;
import com.bohniman.vmsmaintenance.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // ========================================================================
    // VEHICLE SEARCH PAGE
    // ========================================================================
    @GetMapping(value = "/vehicle")
    public ModelAndView pageVehicleSearch(ModelAndView mv) {
        mv = new ModelAndView("store/vehicle_search");

        return mv;
    }

    // ========================================================================
    // VENDOR PAGE
    // ========================================================================
    @GetMapping(value = "/vendor")
    public ModelAndView pageVendorSearch(ModelAndView mv) {
        mv = new ModelAndView("store/vendor_search");

        return mv;
    }

    // ========================================================================
    // VENDOR ALL LIST
    // ========================================================================
    @GetMapping(value = "/vendor/all")
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllVendors() {
        JsonResponse res = storeService.getAllVendors();
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // VENDOR ALL ITEMS PAGE
    // ========================================================================
    @GetMapping(value = "/vendor/allItems/{vendorId}")
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllVendorItem(@PathVariable("vendorId") Long vendorId) {
        JsonResponse res = storeService.getAllVendorItems(vendorId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // ADD NEW VENDOR
    // ========================================================================
    @PostMapping(value = { "/vendor/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addVendor(@Valid @ModelAttribute MasterVendor masterVendor,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewVendor(masterVendor);
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
    // VENDOR DETAILS PAGE
    // ========================================================================
    @GetMapping(value = { "/vendor/{vendorId}" })
    public ModelAndView pageVendorDetail(ModelAndView mv, @PathVariable("vendorId") Long vendorId) {
        mv = new ModelAndView("store/vendor_detail");
        mv.addObject("unitList", new InventoryUnitService().getAll());
        mv.addObject("vendor", storeService.getVendorById(vendorId));
        return mv;
    }

    // ========================================================================
    // DELETE VENDOR DETAILS
    // ========================================================================
    @DeleteMapping(value = { "/vendor/delete/{vendorId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteVendor(@PathVariable("vendorId") Long vendorId) {
        JsonResponse res = new JsonResponse();
        res = storeService.deleteVendorById(vendorId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // ADD NEW VENDOR ITEM
    // ========================================================================
    @PostMapping(value = { "/vendor/addItem" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addVendorItem(@Valid @ModelAttribute MasterVendorItem masterVendorItem,
            BindingResult bindingResult) throws BindException {
        System.out.println(masterVendorItem);
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewVendorItem(masterVendorItem);
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
    // DELETE VENDOR ITEM
    // ========================================================================
    @DeleteMapping(value = { "/vendor/deleteItem/{itemId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteVendorItem(@PathVariable("itemId") Long itemId) {
        JsonResponse res = new JsonResponse();
        res = storeService.deleteVendorItemById(itemId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // VEHICLE DETAIL PAGE
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}")
    public ModelAndView pageVehicleDetail(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/vehicle_detail");
        mv.addObject("vehicleId", vehicleId);
        return mv;
    }

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
    // ADD NEW VEHICLE
    // ========================================================================
    @PostMapping(value = { "/addVehicle" })
    @ResponseBody
    public boolean addVehicle(@ModelAttribute("newVehicle") MasterVehicle newVehicle) {
        return storeService.saveNewVehicle(newVehicle);
    }

    // ========================================================================
    // DELETE VEHICLE
    // ========================================================================
    @PostMapping(value = { "/deleteVehicle/{vehicleId}" })
    @ResponseBody
    public boolean deleteVehicle(@PathVariable("vehicleId") Long vehicleId) {
        return storeService.deleteVehicleById(vehicleId);
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

    // ========================================================================
    // OPEN JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}/open-job-card")
    public ModelAndView openJobCard(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/open_job_card");
        mv.addObject("vehicleId", vehicleId);
        return mv;
    }

    // ========================================================================
    // VIEW JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}/view-job-card")
    public ModelAndView viewJobCard(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/view_job_card");
        mv.addObject("vehicleId", vehicleId);
        return mv;
    }

    // ========================================================================
    // PAGE JOB CARD HOME
    // ========================================================================
    @GetMapping(value = "/job-card")
    public ModelAndView jobCardHome(ModelAndView mv) {
        mv = new ModelAndView("store/job_card_home");
        return mv;
    }

    // ========================================================================
    // PAGE ORDER SHEET HOME
    // ========================================================================
    @GetMapping(value = "/order-sheet")
    public ModelAndView orderSheetHome(ModelAndView mv) {
        mv = new ModelAndView("store/order_sheet_home");
        return mv;
    }
}