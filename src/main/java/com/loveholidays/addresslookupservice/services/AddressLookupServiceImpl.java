package com.loveholidays.addresslookupservice.services;

import com.loveholidays.addresslookupservice.configuration.ThirdPartyAddressServiceConfiguration;
import com.loveholidays.addresslookupservice.model.thirdparty.Address;
import com.loveholidays.addresslookupservice.model.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service provide address lookup functionality while satisfying different criteria e.g findAddressByPostCode etc
 *
 * @author mdogar
 */
@Service
public class AddressLookupServiceImpl implements AddressLookupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressLookupServiceImpl.class);

    @Autowired
    ThirdPartyAddressServiceConfiguration addressServiceConfiguration;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Find address for provided post code
     * @param postCode
     * @return AddressResponse
     */
    @Override
    public AddressResponse findAddressByPostCode(String postCode) {
        LOGGER.debug("calling third party service for postCode: {}, to find street name", postCode);
        final Address address = restTemplate.getForObject(
                addressServiceConfiguration.getUrl() + "key=" + addressServiceConfiguration.getKey() +
                        "&query=" + postCode + "&country=" + addressServiceConfiguration.getCountry(), Address.class);

        return mapToAddressResponse(address);
    }

    //TODO: adding a mapper could be more suitable approach here
    private AddressResponse mapToAddressResponse(Address thirdPartyAddress) {
    LOGGER.debug("mapping third party returned address: {}", thirdPartyAddress);
        //TODO: below logic need to improve
        final String[] split = thirdPartyAddress.getResults().get(0).getLabels().get(1).split(",");
        AddressResponse address = new AddressResponse();
        address.setStreetName(split[split.length - 2].trim());
        return address;
    }
}
