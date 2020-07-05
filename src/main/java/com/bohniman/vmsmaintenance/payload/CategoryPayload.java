package com.bohniman.vmsmaintenance.payload;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPayload {
    @NotNull(message = "Id is required")
    private int id;

    @NotNull(message = "Category name Required")
    private String name;
}