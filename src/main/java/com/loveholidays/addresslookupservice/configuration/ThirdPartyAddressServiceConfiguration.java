package com.loveholidays.addresslookupservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Ready configurations from default spring configuration
 *
 * Created by mdogar on 23/04/2017.
 */
@Component
@ConfigurationProperties(prefix = "com.thirdparty.address.service")
public class ThirdPartyAddressServiceConfiguration {

    private String url;
    private String key;
    private String country;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getCountry() {
        return country;
    }
}
