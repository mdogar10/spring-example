package com.loveholidays.addresslookupservice.common;

import com.loveholidays.addresslookupservice.model.thirdparty.Address;
import com.loveholidays.addresslookupservice.model.thirdparty.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdogar on 23/04/2017.
 */
public class TestData {

    /**
     * construct
     * @return
     */
    public static Address getSampleAddress() {
        final Address address = new Address();
        final List<Result> results = new ArrayList<>();
        final Result result = new Result();
        result.setId("1");
        result.setCount(1);
        final List<String> labels = new ArrayList<>();
        labels.add("BT48 6DQ");
        labels.add("this is a test address, test Building, street name, test city");
        result.setLabels(labels);
        results.add(result);
        address.setResults(results);
        return address;
    }
}
