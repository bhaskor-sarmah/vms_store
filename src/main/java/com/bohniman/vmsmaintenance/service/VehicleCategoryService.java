package com.bohniman.vmsmaintenance.service;

import java.util.ArrayList;
import java.util.List;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.payload.VehicleCategoryMaster;

import org.springframework.stereotype.Service;

/**
 * VehicleCategoryService
 */
@Service
public class VehicleCategoryService {
    public List<VehicleCategoryMaster> getAll() {
        List<VehicleCategoryMaster> list = new ArrayList<VehicleCategoryMaster>();
        list.add(new VehicleCategoryMaster(1, "Own"));
        list.add(new VehicleCategoryMaster(2, "Hired"));
        return list;
    }

    public VehicleCategoryMaster getById(int getId) {
        VehicleCategoryMaster vehicleCategoryMaster = null;
        for (VehicleCategoryMaster v : getAll()) {
            if (v.getId() == getId) {
                vehicleCategoryMaster = v;
                break;
            }
        }
        if (vehicleCategoryMaster != null) {
            return vehicleCategoryMaster;
        } else {
            System.out.println("Vehicle Category Id not Found.");
            throw new BadRequestException("Invalid id");
        }
    }

}