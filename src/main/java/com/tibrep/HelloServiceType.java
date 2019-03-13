package com.tibrep;



import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.spi.ServiceType;

import java.util.Objects;

public interface HelloServiceType extends ServiceType {
    String TYPE ="hello-type";



    /**
     * This method is helper method to create the service recored when you have the name, location and metaData
     * Many of such function can be implemented here like publish service
     * @param aName aName of the service
     * @param aLocation a location fo the service
     * @param aMetaData a MetaData needed to locate the service record
     * @return Service Record
     */
    static Record createRecord(String aName, JsonObject aLocation, JsonObject aMetaData)
    {
        Objects.requireNonNull(aLocation);
        Objects.requireNonNull(aName);
        Record rec = new Record().setName(aName).setLocation(aLocation);
        if(aMetaData != null ) {
        rec.setMetadata(aMetaData);
        }
        return rec;
    }
}
