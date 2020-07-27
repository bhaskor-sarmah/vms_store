package com.bohniman.vmsmaintenance.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.TransJobCardItemOrder;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.service.DcpService;
import com.bohniman.vmsmaintenance.utilities.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dcp")
public class DcpController {

    @Autowired
    DcpService dcpService;
    
    // ========================================================================
    // DCP DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageMtoAdminDashboard(ModelAndView mv) {
        mv = new ModelAndView("dcp/dashboard");

        return mv;
    }

    // ========================================================================
    // PAGE ORDER SHEET HOME
    // ========================================================================
    @GetMapping(value = "/order-sheet")
    public ModelAndView orderSheetHome(ModelAndView mv, @RequestParam Optional<String> fromDate,
            @RequestParam Optional<String> toDate) throws java.text.ParseException {
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

        mv = new ModelAndView("dcp/ordersheet_home");

        List<TransJobCardItemOrder> orderSheets = dcpService.getOrderSheetsByDateRange(calendarFrom.getTime(), calendarTo.getTime());
        mv.addObject("orderSheets", orderSheets);
        mv.addObject("dateFrom", dateFrom);
        mv.addObject("dateTo", dateTo);

        return mv;
    }

    // ========================================================================
    // FORWARD JOB CARD
    // ========================================================================
    @PutMapping(value = { "/vehicle/job-card/order/change-status/{orderId}/{statusType}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> orderChangeStatus(@PathVariable("orderId") Long orderId, @PathVariable("statusType") String statusType){

        JsonResponse res = dcpService.changeOrderStatus(orderId, statusType);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // ALL ITEM OF A VENDOR AGAINST AN ORDER AND JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/getItemByOrder")
    @ResponseBody
    public ResponseEntity<JsonResponse> getItemByOrder(@RequestParam("orderId") Long orderId,
            @RequestParam("jobcardId") Long jobcardId, @RequestParam("vendorId") Long vendorId) {
        JsonResponse res = dcpService.getAllItemByOrder(orderId, vendorId, jobcardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }
}