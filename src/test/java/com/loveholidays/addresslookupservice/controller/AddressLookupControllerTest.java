package com.loveholidays.addresslookupservice.controller;

import com.loveholidays.addresslookupservice.model.thirdparty.Address;
import com.loveholidays.addresslookupservice.server.AddressLookupApplicationServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static com.loveholidays.addresslookupservice.common.TestData.getSampleAddress;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by mdogar on 27/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressLookupApplicationServer.class)
@WebAppConfiguration
public class AddressLookupControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        when(restTemplate.getForObject("https://api.craftyclicks.co.uk/address/1.1/find?key=a63bc-68371-f2dae-020c5&query=BT48 6DQ&country=gb", Address.class)).thenReturn(getSampleAddress());

    }

    @Test
    public void test_FindStreetNameSuccessfullyWhenAValidPostCodeProvided() throws Exception {
        mockMvc.perform(get("/api/v1/address/postcode/BT48 6DQ").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.streetName", is("street name")));
    }

    //TODO: add more test scenarios for negative as well as for different combination of correct postcodes e.g G12 8EL etc
}
