package com.github.kuzan7.vertx_kotlin_start.common

import io.vertx.ext.web.Router
import io.vertx.kotlin.core.http.listenAwait

class BaseHttpServerVerticle : EnhanceCoroutineVerticle() {

  override suspend fun start() {
    println(Thread.currentThread().name)
    val httpServer = vertx.createHttpServer()

    httpServer.exceptionHandler { it.printStackTrace() }

    val router = Router.router(vertx)

    var routerRegistry = RouterRegistry.instance
    routerRegistry.forEach {
      it.register(router)
      println("register router $router")
    }

    router.route().failureHandler {
      it.failure().printStackTrace()
      it.response().end("error")
    }

    httpServer.requestHandler(router)
    httpServer.listenAwait(8080)
    println("server start on port: ${httpServer.actualPort()}")
  }
}
