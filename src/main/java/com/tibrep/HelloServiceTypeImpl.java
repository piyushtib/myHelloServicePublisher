package com.tibrep;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;

public class HelloServiceTypeImpl implements HelloServiceType {
    @Override
    public String name() {
        return TYPE;
    }

    @Override
    public ServiceReference get(Vertx vertx, ServiceDiscovery serviceDiscovery, Record record, JsonObject conf) {
        return new HelloServiceRef(vertx,serviceDiscovery,record,conf);
    }
}
