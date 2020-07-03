package com.bohniman.vmsmaintenance.payload;

import java.util.ArrayList;
import java.util.List;

/**
 * PageableObjectPayload
 */
public class PageableObjectPayload {

    PageDetails pageDetails;
    List<?> objectList1 = new ArrayList<>();
    List<?> objectList2 = new ArrayList<>();
    Object currentObject1;
    Object currentObject2;


}