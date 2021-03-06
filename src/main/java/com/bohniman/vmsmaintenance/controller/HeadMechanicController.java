package com.bohniman.vmsmaintenance.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.exception.MyResourceNotFoundException;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCard;
import com.bohniman.vmsmaintenance.payload.JsonResponse;
import com.bohniman.vmsmaintenance.service.HeadmechanicService;
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
@RequestMapping("/headmechanic")
public class HeadMechanicController {

    @Autowired
    HeadmechanicService headMechanicService;
    
    // ========================================================================
    // HEADMECHANIC DASHBOARD PAGE
    // ========================================================================
    @GetMapping(value = "")
    public ModelAndView pageHeadMechanicDashboard(ModelAndView mv) {
        mv = new ModelAndView("headmechanic/dashboard");

        return mv;
    }

    // ========================================================================
    // PAGE JOB CARD HOME
    // ========================================================================
    @GetMapping(value = "/job-card")
    public ModelAndView jobCardHome(ModelAndView mv, @RequestParam Optional<String> fromDate,
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

        mv = new ModelAndView("headmechanic/job_card_home");

        List<TransVehicleJobCard> jobCards = headMechanicService.getJobCardsByDateRange(calendarFrom.getTime(), calendarTo.getTime());
        mv.addObject("jobCards", jobCards);
        mv.addObject("dateFrom", dateFrom);
        mv.addObject("dateTo", dateTo);

        return mv;
    }

    // ========================================================================
    // JOB CARD CHANGE STATUS
    // ========================================================================
    @PutMapping(value = { "/vehicle/job-card/change-status/{jobCardId}/{statusType}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> jobCardChangeStatus(@PathVariable("jobCardId") Long jobCardId, @PathVariable("statusType") String statusType){

        JsonResponse res = headMechanicService.changeJobCardStatus(jobCardId, statusType);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }

    // ========================================================================
    // VIEW JOB CARD
    // ========================================================================
    @GetMapping(value = "/vehicle/job-card/{jobCardId}")
    public ModelAndView viewJobCard(ModelAndView mv, @PathVariable("jobCardId") Long jobCardId) {
        mv = new ModelAndView("headmechanic/view_job_card");

        TransVehicleJobCard transVehicleJobCard = headMechanicService.getJobCardById(jobCardId);
        mv.addObject("transVehicleJobCard", transVehicleJobCard);

        

        return mv;
    }

    // ========================================================================
    // GET ALL JOB CARD ITEMS
    // ========================================================================
    @GetMapping(value = { "/vehicle/job-card/get-all-items/{jobCardId}" })
    @ResponseBody
    public ResponseEntity<JsonResponse> getJobCardItemList(@PathVariable("jobCardId") Long jobCardId) {

        JsonResponse res = headMechanicService.getJobCardItemList(jobCardId);
        if (res.getResult()) {
            return ResponseEntity.ok(res);
        } else {
            throw new MyResourceNotFoundException(res.getMessage());
        }
    }
}