package com.lam;

import com.lam.core.Server;
import io.vertx.core.Vertx;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Server());
//        vertx.deployVerticle(new com.lam.web.Server());
    }
}
