package com.github.kuzan7.vertx_kotlin_start.common

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.MySQLClient
import io.vertx.ext.sql.SQLClient

class MysqlClientManager : AbstractVerticle() {

  companion object {
    lateinit var dbClient: SQLClient

    fun init(vertx: Vertx) {


      val mysqlConfig: JsonObject = JsonObject()
        .put("host", "-")
        .put("username", "-")
        .put("port", 3306)
        .put("password", "-")
        .put("maxConnectionRetries", 30)
        .put("connectionRetryDelay", 2)
        .put("database", "-")
      dbClient = MySQLClient.createShared(vertx, mysqlConfig)
    }
  }





}
