package com.github.kuzan7.vertx_kotlin_start.login

import com.github.kuzan7.vertx_kotlin_start.common.HttpRouterVerticle
import com.github.kuzan7.vertx_kotlin_start.common.MysqlClientManager
import com.github.kuzan7.vertx_kotlin_start.common.RestResult
import com.github.kuzan7.vertx_kotlin_start.util.PasswordUtil
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpMethod
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.sql.ResultSet
import io.vertx.ext.sql.SQLClient
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.awaitEvent
import io.vertx.kotlin.coroutines.awaitResult

/**
 * 登录
 *
 * curl -XPOST -d '{"userName":"zs","password":"111111"}' http://localhost:8080/login
 */
class LoginVerticle : HttpRouterVerticle() {

  override fun path() = "/login"

  override fun httpMethod() = HttpMethod.POST

  private var dbClient: SQLClient = MysqlClientManager.dbClient

  override suspend fun routerHandler(routingContext: RoutingContext): Any {
    var bodyBuffer = awaitEvent<Buffer> {
      routingContext.request().bodyHandler(it)
    }

    var body = JsonObject(String(bodyBuffer.bytes))
    val userName = body.getString("userName")
    var password = body.getString("password")
    if(userName == null || password == null){
      return RestResult.fail("用户名或密码不能为空。")
    }

    password = PasswordUtil.encodePassword(password, userName)

    var result = awaitResult<ResultSet> {
      dbClient.queryWithParams("select 1 from login where user_name = ? and password = ?",
        JsonArray().add(userName).add(password), it)
    }

    if(result.rows.size == 0){
      return RestResult.fail("用户名或密码错误")
    }

    return RestResult.ok()
  }
}
