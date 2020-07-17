package com.bohniman.vmsmaintenance.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransBill {

    private String billNo;
    private String billDate;
    private String billAmount;
    private String invoiceNo;

    List<TransChallan> challanList;
}