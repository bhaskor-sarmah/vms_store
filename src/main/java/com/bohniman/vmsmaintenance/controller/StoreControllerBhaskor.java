package com.bohniman.vmsmaintenance.controller;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.MasterRack;
import com.bohniman.vmsmaintenance.model.MasterShelves;
import com.bohniman.vmsmaintenance.model.MasterVendor;
import com.bohniman.vmsmaintenance.model.TransChallan;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.model.TransVendorItem;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.payload.NewPurchaseItemPayload;
import com.bohniman.vmsmaintenance.service.InventoryUnitService;
import com.bohniman.vmsmaintenance.service.StoreServiceBhaskor;
import com.bohniman.vmsmaintenance.utilities.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        // System.out.println(masterShelves);
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

    // ========================================================================
    // JOB CARD VENDOR ORDER **** MOVED FROM RITUSMOI CONTROLLER ****
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/{jobCardId}/vendor-order")
    public ModelAndView jobCardVendorOrder(ModelAndView mv, @PathVariable("jobCardId") Long jobCardId) {
        mv = new ModelAndView("store/view_job_card_vendor_order");
        mv.addObject("vendorOrderItemPayload", storeService.getVendorOrderItemPayload(jobCardId));
        TransVehicleJobCard transVehicleJobCard = storeService.getJobCardById(jobCardId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);
        return mv;
    }

    // ========================================================================
    // ALL ITEM OF A VENDOR AGAINST AN ORDER AND JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/getItemByOrder")
    @ResponseBody
    public ResponseEntity<JsonResponse> getItemByOrder(@RequestParam("orderId") Long orderId,
            @RequestParam("jobcardId") Long jobcardId, @RequestParam("vendorId") Long vendorId) {
        JsonResponse res = storeService.getAllItemByOrder(orderId, vendorId, jobcardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // GENERATE ORDER OF ALL THE FRESH ITEMS OF A VENDOR AGAINST A JOB CARD
    // ========================================================================
    @PostMapping(value = "/vehicle/job-card/generateOrder")
    @ResponseBody
    public ResponseEntity<JsonResponse> generateOrder(@RequestParam("jobCardId") Long jobCardId,
            @RequestParam("vendorId") Long vendorId) {
        JsonResponse res = storeService.generateOrder(vendorId, jobCardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // UPDATE ORDER STATUS AS PDF DOWNLOADED
    // ========================================================================
    @PostMapping(value = "/vehicle/job-card/downloadOrder")
    @ResponseBody
    public ResponseEntity<JsonResponse> downloadOrder(@RequestParam("orderId") Long orderId) {
        JsonResponse res = storeService.downloadOrder(orderId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // DOWNLOAD ORDER PDF
    // ========================================================================
    @GetMapping(value = { "/vehicle/job-card/downloadOrderPdf/{orderId}" }, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadOrderPdf(@PathVariable("orderId") Long orderId) {
        // boolean res = true;
        // if (session.getAttribute("mobile") == null) {
        // res = false;
        // }
        // String mobile = session.getAttribute("mobile").toString();
        // Journey journey = citizenService.getJourneyByCitizen(mobile);
        // journey.setFromPoliceStation(masterService.getPoliceStationById(journey.getFromPoliceStation()));
        // journey.setToPoliceStation(masterService.getPoliceStationById(journey.getToPoliceStation()));
        // if (journey == null || !id.equals(String.valueOf(journey.getId()))) {
        // res = false;
        // }
        ByteArrayInputStream bis = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            bis = storeService.generateOrderPdf(orderId);
            headers.add("Content-Disposition", "inline;filename=order.pdf");
            headers.setContentDispositionFormData("Content-Disposition", "order.pdf");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            bis = storeService.generateOrderPdf(0L);
            headers.add("Content-Disposition", "inline; filename=error.pdf");
            headers.setContentDispositionFormData("Content-Disposition", "order.pdf");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        }
    }

    // ========================================================================
    // GET CHALLAN ENTRY PAGE
    // ========================================================================
    @PostMapping(value = "/vehicle/job-card/challanEntry")
    public ModelAndView challanEntryPage(ModelAndView mv, @RequestParam("orderId") Long orderId,
            @RequestParam("jobCardId") Long jobCardId) {
        mv = new ModelAndView("store/challan_entry");
        mv.addObject("order", storeService.getOrderById(orderId));
        mv.addObject("transVehicleJobCard", storeService.getJobCardById(jobCardId));
        mv.addObject("itemList", storeService.getItemListNotInChallanByOrderId(orderId));
        mv.addObject("challan", new TransChallan());
        return mv;
    }

    // ========================================================================
    // ALL CHALLANS OF AN ORDER
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/allChallan/{orderId}")
    @ResponseBody
    public ResponseEntity<JsonResponse> getAllChallan(@PathVariable("orderId") Long orderId) {
        JsonResponse res = storeService.getAllChallanByOrder(orderId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }

    // ========================================================================
    // NEW CHALLAN ENTRY
    // ========================================================================
    @PostMapping(value = "/vehicle/job-card/newChallan")
    public ModelAndView newChallan(ModelAndView mv, @Valid @ModelAttribute("challan") TransChallan transChallan,
            BindingResult bindingResult, @RequestParam("jobCardId") Long jobCardId,
            @RequestParam(value = "transVendorItemId[]") Long[] transVendorItemId,
            @RequestParam(value = "noOfItem[]") Double[] noOfItem,
            @RequestParam(value = "warrantyUpto[]") String[] warrantyUpto,
            @RequestParam(value = "quantityRemainingToReceive[]") Double[] quantityRemainingToReceive,
            @RequestParam(value = "orderQuantity[]") Double[] orderQuantity) {

        if (storeService.checkIfChallanExist(transChallan.getChallanNo())) {
            bindingResult.rejectValue("challanNo", "CustomError", "Challan No already exist !");
        }

        // System.out.println(transChallan.toString());
        // System.out.println(jobCardId);
        // System.out.println(transVendorItemId.length);
        // System.out.println(warrantyUpto[0]);

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        List<NewPurchaseItemPayload> newPurchaseItemList = new ArrayList<>();
        for (int i = 0; i < transVendorItemId.length; i++) {
            Date warrantyDate = null;
            if (noOfItem[i] == null || noOfItem[i] == 0) {
                continue;
            }
            if (warrantyUpto[i] != "") {
                if (warrantyUpto[i].length() == 10 && DateUtil.isValidDate(warrantyUpto[i])) {
                    try {
                        warrantyDate = sdf.parse(warrantyUpto[i]);
                    } catch (Exception e) {

                    }
                } else {
                    bindingResult.rejectValue("noOfItems", "CustomError",
                            "Invalid Date provided for item no " + (i + 1));
                    break;
                }
            }
            if (noOfItem[i] > quantityRemainingToReceive[i]) {
                bindingResult.rejectValue("noOfItems", "CustomError",
                        "Quantity greater than Remaining quantity for item no " + (i + 1));
                break;
            }
            NewPurchaseItemPayload newPurchaseItem = new NewPurchaseItemPayload();
            newPurchaseItem.setNoOfItem(noOfItem[i]);
            newPurchaseItem.setOrderQuantity(orderQuantity[i]);
            newPurchaseItem.setQuantityRemainingToReceive(quantityRemainingToReceive[i]);
            newPurchaseItem.setTransVendorItemId(transVendorItemId[i]);
            newPurchaseItem.setWarrantyUpto(warrantyDate);
            newPurchaseItemList.add(newPurchaseItem);
        }
        if (!bindingResult.hasErrors()) {
            if (storeService.saveChallan(transChallan, jobCardId, newPurchaseItemList)) {
                mv = new ModelAndView("store/challan_entry");
                mv.addObject("order", storeService.getOrderById(transChallan.getOrder().getId()));
                mv.addObject("transVehicleJobCard", storeService.getJobCardById(jobCardId));
                mv.addObject("itemList",
                        storeService.getItemListNotInChallanByOrderId(transChallan.getOrder().getId()));
                mv.addObject("challan", new TransChallan());
                mv.addObject("msgSuccess", "New Challan saved successfully !");
                return mv;
            } else {
                mv = new ModelAndView("store/challan_entry");
                mv.addObject("order", storeService.getOrderById(transChallan.getOrder().getId()));
                mv.addObject("transVehicleJobCard", storeService.getJobCardById(jobCardId));
                mv.addObject("itemList",
                        storeService.getItemListNotInChallanByOrderId(transChallan.getOrder().getId()));
                mv.addObject("challan", new TransChallan());
                mv.addObject("msgErr", "Some Error has ocurred !");
                return mv;
            }
        } else {
            mv = new ModelAndView("store/challan_entry");
            mv.addObject("order", storeService.getOrderById(transChallan.getOrder().getId()));
            mv.addObject("transVehicleJobCard", storeService.getJobCardById(jobCardId));
            mv.addObject("itemList", storeService.getItemListNotInChallanByOrderId(transChallan.getOrder().getId()));
            mv.addObject("challan", transChallan);
            mv.addObject("msgErr", "There are validation error !");
            return mv;
        }
    }

    // ========================================================================
    // ALL ITEM OF A CHALLAN
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/getItemByChallan")
    @ResponseBody
    public ResponseEntity<JsonResponse> getItemByChallan(@RequestParam("challanId") Long challanId) {
        JsonResponse res = storeService.getAllItemByChallan(challanId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }
}