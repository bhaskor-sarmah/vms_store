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
}