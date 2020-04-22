package com.github.kuzan7.vertx_kotlin_start.common

import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch

open class EnhanceCoroutineVerticle : CoroutineVerticle(){

  /**
   * http request body coroutine handler
   */
  suspend fun HttpServerRequest.coroutineBodyHandler(fn: suspend (Buffer) -> Unit) {
    bodyHandler {
      launch {
        fn(it)
      }
    }
  }

  /**
   * message consumer coroutine handler
   */
  suspend fun <T> MessageConsumer<T>.coroutineHandler(fn: suspend (T) -> Unit) {
    handler { ctx ->
      launch {
        fn(ctx.body())
      }
    }
  }

  /**
   * An extension method for simplifying coroutines usage with Vert.x Web routers
   */
  fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
    handler { ctx ->
      launch(ctx.vertx().dispatcher()) {
        try {
          fn(ctx)
        } catch (e: Exception) {
          ctx.fail(e)
        }
      }
    }
  }

}
