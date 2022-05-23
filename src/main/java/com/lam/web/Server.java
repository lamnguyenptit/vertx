package com.lam.web;

import com.lam.web.model.Article;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Server extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);
        router.get("/api/v1/articles/article/:id")
                .handler(this::getArticles);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", 8080), httpServerAsyncResult -> {
                    if (httpServerAsyncResult.succeeded()) {
                        startPromise.complete();
                    } else {
                        startPromise.fail(httpServerAsyncResult.cause());
                    }
                });
    }

    private void getArticles(RoutingContext routingContext) {
        String articleId = routingContext.request()
                .getParam("id");
        Article article = new Article(articleId, "This is an intro to vertx", "lam", "23/05/2022", 1999);
        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(article));
    }
}
