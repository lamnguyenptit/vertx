package com.lam.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Server extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.createHttpServer()
                .requestHandler(httpServerRequest -> httpServerRequest.response().putHeader("content-type", "text/plain").end("Hello world"))
                .listen(config().getInteger("http.port", 9090),
                        httpServerAsyncResult -> {
                            if (httpServerAsyncResult.succeeded()) {
                                startPromise.complete();
                            } else {
                                startPromise.fail(httpServerAsyncResult.cause());
                            }
                        });
    }

//    @Override
//    public void start(Promise<Void> startPromise) {
//        System.out.println("Hello world");
//    }

    @Override
    public void stop() {
        System.out.println("Shutting down application");
    }
}
