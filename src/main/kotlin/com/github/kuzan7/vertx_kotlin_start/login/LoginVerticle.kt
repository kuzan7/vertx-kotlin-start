package com.github.kuzan7.vertx_kotlin_start.login

import com.github.kuzan7.vertx_kotlin_start.common.HttpRouterVerticle
import com.github.kuzan7.vertx_kotlin_start.common.RestResult
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpMethod
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.MySQLClient
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.awaitEvent

/**
 * 登录
 *
 * curl -XPOST -d '{"userName":"zs","password":"111111"}' http://localhost:8080/login
 */
class LoginVerticle : HttpRouterVerticle() {

  override fun path() = "/login"

  override fun httpMethod() = HttpMethod.POST

  private lateinit var dbClient: SQLClient

  override suspend fun doInit(){

    val mysqlConfig: JsonObject = JsonObject()
      .put("host", "127.0.0.1")
      .put("username", "root")
      .put("port", 33306)
      .put("password", "123456")
      .put("maxConnectionRetries", 30)
      .put("connectionRetryDelay", 2)
      .put("database", "flow")
    dbClient = MySQLClient.createShared(vertx, mysqlConfig)
  }

  override suspend fun routerHandler(routingContext: RoutingContext): Any {
    var bodyBuffer = awaitEvent<Buffer> {
      routingContext.request().bodyHandler(it)
    }
    var body = JsonObject(String(bodyBuffer.bytes))
    val userName = body.getString("userName")
    val password = body.getString("password")
    if(userName == null || password == null){
      return RestResult.fail("用户名或密码不能为空。")
    }
    if(!userName.equals("zs") || !password.equals("111111")){
      return RestResult.fail("用户名或密码错误")
    }

    return RestResult.ok()
  }
}
