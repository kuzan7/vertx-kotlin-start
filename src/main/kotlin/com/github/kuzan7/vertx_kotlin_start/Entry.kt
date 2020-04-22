package com.github.kuzan7.vertx_kotlin_start

import com.github.kuzan7.vertx_kotlin_start.common.BaseHttpServerVerticle
import com.github.kuzan7.vertx_kotlin_start.login.LoginVerticle
import io.vertx.core.Vertx

fun main(args: Array<String>) {
  val vertX = Vertx.vertx()
  vertX.deployVerticle(LoginVerticle())

  //最后一个执行
  vertX.deployVerticle(BaseHttpServerVerticle())
}
