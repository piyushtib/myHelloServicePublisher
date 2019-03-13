package com.tibrep;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;
import io.vertx.servicediscovery.types.AbstractServiceReference;

public class HelloServiceRef extends AbstractServiceReference<HelloServiceInterface> {
    JsonObject conf;

    public HelloServiceRef(Vertx vertx, ServiceDiscovery serviceDiscovery, Record record, JsonObject conf) {
        super(vertx,serviceDiscovery,record);
        this.conf = conf;
    }

    @Override
    protected HelloServiceInterface retrieve() {

        return new HelloServiceInterfaceImpl();
    }
}
