package com.timabil.services

import com.results.Result

abstract class SystemClient<in MESSAGE, out SUCCESS, out FAILURE> {
    abstract fun send(message: MESSAGE): Result<SUCCESS, FAILURE>
}