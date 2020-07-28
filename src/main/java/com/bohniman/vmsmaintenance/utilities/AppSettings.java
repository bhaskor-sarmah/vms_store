package com.bohniman.vmsmaintenance.utilities;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    // DONOT CHANGE ANY OF THE ORDER STATUS ! THERE IS LOGIC IN THE FRONT END BASED
    // ON THESE STRINGS
    public static final String ORDER_STATUS_FRESH = "FRESH";
    public static final String ORDER_STATUS_PENDING = "PENDING"; // APPROVAL PENDING
    public static final String ORDER_STATUS_APPROVED = "APPROVED";
    public static final String ORDER_STATUS_PLACED = "PLACED";
    public static final String ORDER_STATUS_RECEIVED = "RECEIVED";
    public static final String ORDER_STATUS_CLOSED = "CLOSED";

    public static final String VENDOR_ITEM_STATUS_ACTIVE = "ACTIVE";

    public static final String PURCHASE_ITEM_STATUS_FRESH = "FRESH";
    public static final String PURCHASE_ITEM_STATUS_USED = "USED";
    public static final String PURCHASE_ITEM_STATUS_AUCTION = "AUCTION";
    public static final String PURCHASE_ITEM_STATUS_SCRAP = "SCRAP";

    public static final String PURCHASE_ITEM_TYPE_NEW = "NEW";
    public static final String PURCHASE_ITEM_TYPE_OLD = "OLD";

    public static final String TRANS_BILL_STATUS_RECEIVED = "RECEIVED";
    public static final String TRANS_BILL_STATUS_APPROVED = "APPROVED";
    public static final String TRANS_BILL_STATUS_PAID = "PAID";

    public static final String MASTER_VEHICLE_STATUS_ACTIVE = "ACTIVE";
    public static final String MASTER_VEHICLE_STATUS_MAINTENANCE = "MAINTENANCE";
    public static final String MASTER_VEHICLE_STATUS_SCRAP = "SCRAP";
}