package com.bohniman.vmsmaintenance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TransBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billNo;
    private String billDate;
    private String billAmount;
    private String invoiceNo;

    @OneToMany(mappedBy = "transBill", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    List<TransChallan> challanList = new ArrayList<>();

    private String status;// RECEIVED, APPROVED, PAID

    public TransBill(String string) {
        this.billNo = string;
    }
}