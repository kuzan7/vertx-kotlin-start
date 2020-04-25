package com.github.kuzan7.vertx_kotlin_start.util

import com.google.common.hash.Hashing
import java.nio.charset.Charset

class PasswordUtil {

  companion object {

    fun encodePassword(password: String, userName: String) : String {
      var tmp = "vertx-$password-kotlin-$userName"
      return Hashing.sha256().hashString(tmp, Charset.forName("utf-8")).toString()
    }

  }

}
