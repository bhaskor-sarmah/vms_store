package com.bohniman.vmsmaintenance.payload;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class VehicleCategoryMaster {
    @NotNull(message = "Id is required")
    private int id;
    @NotNull(message = "Category name Required")
    private String catogoryName;

    public VehicleCategoryMaster() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getCatogoryName() {
        return catogoryName;
    }

    public VehicleCategoryMaster(@NotNull(message = "Id is required") int id,
            @NotNull(message = "Category name Required") String catogoryName) {
        super();
        this.id = id;
        this.catogoryName = catogoryName;
    }
}