package com.loveholidays.addresslookupservice.controller;

import com.loveholidays.addresslookupservice.model.response.AddressResponse;
import com.loveholidays.addresslookupservice.services.AddressLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a rest Controller
@RequestMapping(path = "/api/v1/address")
public class AddressLookupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressLookupController.class);

    @Autowired
    private AddressLookupService addressLookupService;

    /**
     * @param postCode
     * @return
     */

    @GetMapping(path = "/postcode/{value}")
    public AddressResponse findAddress(@PathVariable(name = "value") String postCode) {

        if (postCode == null) throw new IllegalArgumentException();

        LOGGER.info("find address for postcode: {}", postCode);
        return addressLookupService.findAddressByPostCode(postCode);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "postCode missing in the path")
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleConflict() {
        // Nothing to do
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "unable to process request")
    @ExceptionHandler(Exception.class)
    public void handleGenericException() {
        // Nothing to do
    }
}