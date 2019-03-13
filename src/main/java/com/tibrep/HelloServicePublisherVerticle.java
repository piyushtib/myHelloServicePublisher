package com.tibrep;

import io.vertx.core.Launcher;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.ServiceReference;

public class HelloServicePublisherVerticle extends AbstractVerticle{

    Record publishedRec;
    ServiceDiscovery service;
    public static void main(final String[] args) {
        Launcher.executeCommand("run", HelloServicePublisherVerticle.class.getName());
    }

    @Override
    public void start() throws Exception {
        System.out.println("HelloPublisherVerticle Started");
        super.start();

//         service = ServiceDiscovery.create(Vertx.vertx(),
//                    new ServiceDiscoveryOptions().setName(HelloServiceType.TYPE).setAnnounceAddress("hello-service"));

         service = ServiceDiscovery.create(Vertx.vertx());
        Record rec = HelloServiceType.createRecord("hello-service",
                                    new JsonObject().put("host","localhost"),
                                    new JsonObject().put("metadata","metadata"));
        service.publish(rec, ar -> {
            if(ar.succeeded())
            {
                 publishedRec = ar.result();
                System.out.println("Able to publish the record"+publishedRec.getName());
            }
            else {
                System.out.println("Not able to publish the record, ಥ_ಥ ");
            }
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        String serviceName = "hello-service";

        // service.getRecord( r -> serviceName.equals(r.getName()),
        service.getRecord((JsonObject)null,
                ar -> {
                    System.out.println("Looking up for Record");
                    if(ar.result() == null)
                        System.out.println("ar.result is NULL ");

                    if( ar.succeeded() && ar.result() != null ){
//                         ServiceReference helloSerRef = service.getReference(ar.result());
//                                       HelloServiceInterface helloSer = helloSerRef.get();
//                                       helloSer.sayHello("Mr Sudhir");
//                                         helloSerRef.release();
                        System.out.println("Found the Hello Service" + serviceName + ar.result().getName());
                    }
                    else
                    {
                        System.out.println("Could found the Hello Service" + serviceName);
                    }
                });


        service.unpublish(publishedRec.getRegistration(), ar -> {
            
            if(ar.succeeded()){
                System.out.println("Successfully unpublished the service ");
            }
            else
            {
                System.out.println("UNABLE Successfully unpublished the service ");
            }
        });

        service.close();

        System.out.println("HelloPublisherVerticle Stopped");

        super.stop(stopFuture);

    }
}
