package com.bohniman.vmsmaintenance.controller;

import java.util.List;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
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
    // VEHICLE DETAIL PAGE
    // ========================================================================
    @GetMapping(value = "/vehicle/{vehicleId}")
    public ModelAndView pageVehicleDetail(ModelAndView mv, @PathVariable("vehicleId") Long vehicleId) {
        mv = new ModelAndView("store/vehicle_detail");
        mv.addObject("vehicleId", vehicleId);
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
}