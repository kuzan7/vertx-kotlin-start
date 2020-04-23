package com.github.kuzan7.vertx_kotlin_start

import com.github.kuzan7.vertx_kotlin_start.common.BaseHttpServerVerticle
import com.github.kuzan7.vertx_kotlin_start.login.LoginVerticle
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.awaitResult
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
  val vertX = Vertx.vertx()

  awaitResult<String> {
    vertX.deployVerticle(LoginVerticle(), it)
  }

  vertX.deployVerticle(BaseHttpServerVerticle())
}
