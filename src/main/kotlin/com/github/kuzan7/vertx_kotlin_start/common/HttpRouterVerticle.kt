package com.github.kuzan7.vertx_kotlin_start.common

import io.vertx.core.http.HttpMethod
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

abstract class HttpRouterVerticle : EnhanceCoroutineVerticle(), RouterRegister {

  override suspend fun start() {
    RouterRegistry.instance.register(this)
  }

  override fun register(router: Router) {
    var route = when(httpMethod()){
      HttpMethod.GET -> router.get(path())
      HttpMethod.POST -> router.post(path())
      HttpMethod.PUT -> router.put(path())
      HttpMethod.DELETE -> router.delete(path())
      HttpMethod.OPTIONS -> router.options(path())
      HttpMethod.HEAD -> router.head(path())
      HttpMethod.TRACE -> router.trace(path())
      HttpMethod.CONNECT -> router.connect(path())
      HttpMethod.PATCH -> router.patch(path())
      HttpMethod.OTHER -> router.get(path())
    }

    route.coroutineHandler {
      val responseBodyCodec = routerHandler(it)
      it.response().end(Json.encode(responseBodyCodec))
    }
  }

  abstract fun path(): String

  abstract fun httpMethod(): HttpMethod

  abstract suspend fun routerHandler(routingContext: RoutingContext): Any

}
