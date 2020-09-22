package com.mt.wanandroid.api

class ApiException(var code: Int, override var message: String) : RuntimeException()