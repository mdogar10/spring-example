package com.loveholidays.addresslookupservice.model.response;

/**
 * Holds information of address found for a given criteria
 *
 * Created by mdogar on 21/03/2017.
 */
public class AddressResponse {

    private String streetName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                '}';
    }
}
