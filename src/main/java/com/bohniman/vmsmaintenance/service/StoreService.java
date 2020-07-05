package com.bohniman.vmsmaintenance.service;

import java.util.List;

import com.bohniman.vmsmaintenance.model.MasterVehicle;
import com.bohniman.vmsmaintenance.model.MasterVehicleInventory;
import com.bohniman.vmsmaintenance.repository.MasterVehicleInventoryRepository;
import com.bohniman.vmsmaintenance.repository.MasterVehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    MasterVehicleRepository masterVehicleRepository;

    @Autowired
    MasterVehicleInventoryRepository masterVehicleInventoryRepository;

    public boolean saveNewInventory(MasterVehicleInventory newInventory) {
        return masterVehicleInventoryRepository.save(newInventory) != null;
    }

    public boolean deleteInventoryById(Long inventoryId) {
        try {
            masterVehicleInventoryRepository.deleteById(inventoryId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<MasterVehicleInventory> findAllInventory() {
        return masterVehicleInventoryRepository.findAll();
    }

    public MasterVehicleInventory findInventoryById(Long inventoryId) {
        return masterVehicleInventoryRepository.findById(inventoryId).get();
    }

    public boolean saveNewVehicle(MasterVehicle newVehicle) {
        return masterVehicleRepository.save(newVehicle) != null;
    }

    public List<MasterVehicle> getVehicleByNumber(String vehicleNo,Long category) {
        if(category == 0){
            // HIRE + OWN
            return masterVehicleRepository.findAllByVehicleRegistrationNo(vehicleNo);
        }
        
    }
}