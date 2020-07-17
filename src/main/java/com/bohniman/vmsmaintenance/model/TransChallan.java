package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransChallan {

    private String challanNo;
    private Date challanDate;
    private Long totalQuantity;
}