package com.loveholidays.addresslookupservice.services;

import com.loveholidays.addresslookupservice.model.response.AddressResponse;

public interface AddressLookupService {

    AddressResponse findAddressByPostCode(String postCode);
}
