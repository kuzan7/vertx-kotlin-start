package com.github.kuzan7.vertx_kotlin_start.common

class RouterRegistry private constructor() {

  private object SingletonHolder {
    val holder= RouterRegistry()
  }

  companion object {
    val instance = SingletonHolder.holder
  }

  private var routerList: List<RouterRegister> = arrayListOf()

  fun forEach(fn: (RouterRegister) -> Unit){
    routerList.forEach { fn(it) }
  }

  fun register(routerRegister: RouterRegister){
    routerList += routerRegister
  }
}
