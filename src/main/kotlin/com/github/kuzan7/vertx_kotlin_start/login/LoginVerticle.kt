package com.github.kuzan7.vertx_kotlin_start.login

import com.github.kuzan7.vertx_kotlin_start.common.HttpRouterVerticle
import com.github.kuzan7.vertx_kotlin_start.common.RestResult
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.RoutingContext

/**
 * 登录
 */
class LoginVerticle : HttpRouterVerticle() {

  override fun path() = "/login"

  override fun httpMethod() = HttpMethod.GET

  override suspend fun routerHandler(routingContext: RoutingContext): Any {
    return RestResult.ok()
  }
}
