package com.github.kuzan7.vertx_kotlin_start.common

data class RestResult(var code: String = "0", var data: Any? = null, var message: String = "") {

  companion object {
    fun ok() = RestResult()

    fun fail() = RestResult("1")

    fun fail(message: String) = RestResult("1", message)
  }

}
