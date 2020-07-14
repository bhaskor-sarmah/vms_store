package com.bohniman.vmsmaintenance.payload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PageableObjectPayload
 */
@Data
@NoArgsConstructor
public class PageableObjectPayload {

    PageDetails pageDetails;
    List<?> objectList1 = new ArrayList<>();
    List<?> objectList2 = new ArrayList<>();
    Map<String, String> map = new HashMap<String, String>();
    Object currentObject1;
    Object currentObject2;


}