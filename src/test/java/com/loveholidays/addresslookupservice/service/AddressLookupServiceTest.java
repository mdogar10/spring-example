package com.loveholidays.addresslookupservice.service;

import com.loveholidays.addresslookupservice.configuration.ThirdPartyAddressServiceConfiguration;
import com.loveholidays.addresslookupservice.model.response.AddressResponse;
import com.loveholidays.addresslookupservice.model.thirdparty.Address;
import com.loveholidays.addresslookupservice.server.AddressLookupApplicationServer;
import com.loveholidays.addresslookupservice.services.AddressLookupService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.loveholidays.addresslookupservice.common.TestData.getSampleAddress;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by mdogar on 27/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressLookupApplicationServer.class)
public class AddressLookupServiceTest {

    @Autowired
    private AddressLookupService addressLookupService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ThirdPartyAddressServiceConfiguration addressServiceConfiguration;

    @Before
    public void before() {

        when(addressServiceConfiguration.getCountry()).thenReturn("gb");
        when(addressServiceConfiguration.getKey()).thenReturn("a63bc");
        when(addressServiceConfiguration.getUrl()).thenReturn("http://localhost?");
        when(restTemplate.getForObject("http://localhost?key=a63bc&query=BT48 6DQ&country=gb", Address.class)).thenReturn(getSampleAddress());
    }

    @Test
    public void test_FindAddressWhenAValidPostCodeProvided() throws Exception {
        AddressResponse addressResponse = addressLookupService.findAddressByPostCode("BT48 6DQ");
        Assert.assertNotNull(addressResponse);
        Assert.assertEquals(addressResponse.getStreetName(), "street name");
    }
}
