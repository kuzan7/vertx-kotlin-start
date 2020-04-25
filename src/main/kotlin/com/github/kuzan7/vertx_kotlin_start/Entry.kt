package com.github.kuzan7.vertx_kotlin_start

import com.github.kuzan7.vertx_kotlin_start.common.BaseHttpServerVerticle
import com.github.kuzan7.vertx_kotlin_start.common.MysqlClientManager
import com.github.kuzan7.vertx_kotlin_start.login.LoginVerticle
import com.github.kuzan7.vertx_kotlin_start.login.RegisterVerticle
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.awaitResult
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
  val vertX = Vertx.vertx()

  MysqlClientManager.init(vertX)

  awaitResult<String> {
    vertX.deployVerticle(LoginVerticle(), it)
  }

  awaitResult<String> {
    vertX.deployVerticle(RegisterVerticle(), it)
  }

  vertX.deployVerticle(BaseHttpServerVerticle())
}
