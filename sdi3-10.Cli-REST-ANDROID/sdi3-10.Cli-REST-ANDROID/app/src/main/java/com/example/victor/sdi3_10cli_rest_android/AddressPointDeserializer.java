package com.example.victor.sdi3_10cli_rest_android;

import com.example.victor.sdi3_10cli_rest_android.model.AddressPoint;
import com.example.victor.sdi3_10cli_rest_android.model.Trip;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Victor on 04/05/2016.
 */
public class AddressPointDeserializer implements JsonDeserializer<AddressPoint> {
    @Override
    public AddressPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return null;
        }

        return context.deserialize(json, AddressPointProffile.class);
    }

    class AddressPointProffile extends AddressPoint {

    }
}
