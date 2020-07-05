package com.bohniman.vmsmaintenance.service;

import java.util.ArrayList;
import java.util.List;

import com.bohniman.vmsmaintenance.exception.BadRequestException;
import com.bohniman.vmsmaintenance.payload.CategoryPayload;

import org.springframework.stereotype.Component;

@Component
public class InventoryUnitService {
    public List<CategoryPayload> getAll() {
        List<CategoryPayload> list = new ArrayList<CategoryPayload>();
        list.add(new CategoryPayload(1, "Own"));
        list.add(new CategoryPayload(2, "Hired"));
        return list;
    }

    public CategoryPayload getById(int getId) {
        CategoryPayload categoryPayload = null;
        for (CategoryPayload c : getAll()) {
            if (c.getId() == getId) {
                categoryPayload = c;
                break;
            }
        }
        if (categoryPayload != null) {
            return categoryPayload;
        } else {
            System.out.println("Inventory Category Id not Found.");
            throw new BadRequestException("Invalid id");
        }
    }
}