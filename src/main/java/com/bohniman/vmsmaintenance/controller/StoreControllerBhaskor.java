package com.bohniman.vmsmaintenance.controller;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.TransVendorItem;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.service.InventoryUnitService;
import com.bohniman.vmsmaintenance.service.StoreServiceBhaskor;

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
public class StoreControllerBhaskor {

    @Autowired
    StoreServiceBhaskor storeService;

    // ========================================================================
    // VENDOR PAGE
    // ========================================================================
    @GetMapping(value = "/vendor")
    public ModelAndView pageVendorSearch(ModelAndView mv) {
        mv = new ModelAndView("store/vendor_search");
        mv.addObject("stateList", storeService.getAllStates());
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
        mv.addObject("itemList", storeService.getItemByVendorId(vendorId));
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
    public ResponseEntity<JsonResponse> addVendorItem(@Valid @ModelAttribute TransVendorItem masterVendorItem,
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
    // DELETE VENDOR ITEM PRICE
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
    // ADD VENDOR ITEM PRICE
    // ========================================================================
    @PostMapping(value = { "/vendor/itemPrice" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addItemPrice(@RequestParam("itemId") Long itemId,
            @RequestParam("vendorId") Long vendorId, @RequestParam("price") Double price) {
        JsonResponse res = new JsonResponse();
        res = storeService.saveVendorItemPrice(itemId, vendorId, price);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // UPDATE VENDOR ITEM PRICE
    // ========================================================================
    @PostMapping(value = { "/vendor/updateItemPrice" })
    @ResponseBody
    public ResponseEntity<JsonResponse> updateItemPrice(@RequestParam("id") Long itemId,
            @RequestParam("itemPrice") Double itemPrice) throws BindException {
        JsonResponse res = storeService.updateVendorItem(itemId, itemPrice);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // OLD/DAMAGE CAR PARTS PAGE
    // ========================================================================
    @GetMapping(value = "/old-car-parts")
    public ModelAndView pageOldDamageCarParts(ModelAndView mv) {
        mv = new ModelAndView("store/old_car_parts");
        return mv;
    }

    // ========================================================================
    // LIST ALL RACK
    // ========================================================================
    @GetMapping(value = { "/rack/all" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllRacks() {

        JsonResponse res = storeService.getAllRacks();
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // ADD NEW RACK
    // ========================================================================
    @PostMapping(value = { "/rack/add" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addRack(@Valid @ModelAttribute MasterRack masterRack,
            BindingResult bindingResult) throws BindException {
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewRack(masterRack);
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
    // DELETE A RACK
    // ========================================================================
    @DeleteMapping(value = { "/rack/delete/{rackId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteRack(@PathVariable("rackId") Long rackId) {
        JsonResponse res = new JsonResponse();

        res = storeService.deleteRackById(rackId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // RACK DETAILS PAGE
    // ========================================================================
    @GetMapping(value = { "/rack/{rackId}" })
    public ModelAndView pageRackDetail(ModelAndView mv, @PathVariable("rackId") Long rackId) {
        mv = new ModelAndView("store/rack_detail");
        mv.addObject("rack", storeService.getRackById(rackId));
        return mv;
    }

    // ========================================================================
    // ADD NEW SHELVES
    // ========================================================================
    @PostMapping(value = { "/rack/addShelves" })
    @ResponseBody
    public ResponseEntity<JsonResponse> addShelve(@Valid @ModelAttribute MasterShelves masterShelves,
            BindingResult bindingResult) throws BindException {
        System.out.println(masterShelves);
        if (!bindingResult.hasErrors()) {
            JsonResponse res = storeService.saveNewShelves(masterShelves);
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
    // DELETE SHELVES RECORD
    // ========================================================================
    @DeleteMapping(value = { "/rack/deleteShelves/{shelveId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> deleteShelve(@PathVariable("shelveId") Long shelveId) {
        JsonResponse res = new JsonResponse();
        res = storeService.deleteShelveById(shelveId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // ALL SHELVES LIST
    // ========================================================================
    @GetMapping(value = "/rack/allShelves/{rackId}")
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllShelves(@PathVariable("rackId") Long rackId) {
        JsonResponse res = storeService.getAllShelvesByRackId(rackId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // SHELVE DETAILS PAGE
    // ========================================================================
    @GetMapping(value = { "/shelve/{shelveId}" })
    public ModelAndView pageShelveDetail(ModelAndView mv, @PathVariable("shelveId") Long shelveId) {
        mv = new ModelAndView("store/shelve_detail");
        mv.addObject("shelve", storeService.getShelveById(shelveId));
        return mv;
    }

    // ========================================================================
    // ALL SHELVE ITEM LIST
    // ========================================================================
    @GetMapping(value = "/shelve/allItems/{shelveId}")
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllShelveItem(@PathVariable("shelveId") Long shelveId) {
        JsonResponse res = storeService.getAllShelveItemByShelveId(shelveId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // PURCHASE PAGE
    // ========================================================================
    @GetMapping(value = "/purchase")
    public ModelAndView pagePurchaseEntry(ModelAndView mv) {
        mv = new ModelAndView("store/purchase");
        return mv;
    }
}