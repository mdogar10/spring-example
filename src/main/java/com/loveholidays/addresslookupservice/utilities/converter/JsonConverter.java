package com.loveholidays.addresslookupservice.utilities.converter;

import com.google.gson.Gson;

final public class JsonConverter<T> {

    public T stringToJson(Class<T> object, String request){

        return new Gson().fromJson(request,object);
    }
}
