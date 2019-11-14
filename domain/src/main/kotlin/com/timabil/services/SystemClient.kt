package com.timabil.services

import com.results.Result
import com.timabil.interfaces.AuthToken

abstract class SystemClient<in MESSAGE, out SUCCESS, out FAILURE> {
    abstract fun send(message: MESSAGE): Result<SUCCESS, FAILURE>
}

interface SystemClient2<REQUEST, RESPONSE> {
    fun send(request: REQUEST, token: AuthToken): RESPONSE
}