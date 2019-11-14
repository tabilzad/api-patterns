package com.timabil.interfaces

import com.results.Result
import com.timabil.model.ServiceError
import com.timabil.model.ServiceName

interface SystemError<NAME, ERROR_TYPE>

interface SystemOrchestration<SUCCESS, FAILURE, REQUEST> {
    fun orchestrate(request: REQUEST): Result<SUCCESS, FAILURE>
}

interface MessageFactory<in INPUT, out MESSAGE, in DATA_BAG : DataBag> {
    fun make(intput: INPUT, databag: DATA_BAG): MESSAGE
}

interface ResponseService<in INPUT, out OUTPUT> {
    fun toWire(from: INPUT): OUTPUT
}

interface ErrorHandler<SUCCESS, FAILURE>

interface Messenger<MESSAGE, DATABAG, RESPONSE> {
    fun send(message: MESSAGE, databag: DATABAG): RESPONSE
}

abstract class RestErrorHandler<SUCCESS>(private val serviceName: ServiceName) {

    abstract operator fun invoke(call: () -> Any): SUCCESS

    infix fun String.dueTo(ex: Exception) = "Received ${this} while calling ${serviceName.name} due to ${ex.localizedMessage}"
    infix fun ServiceError.dueTo(ex: Exception) = this.message dueTo ex

}

interface AuthToken {
    val authToken: String
        get() = ""
}

inline class JwtToken(override val authToken: String) : AuthToken

object NullToken : AuthToken

interface DataBag {
    val token: AuthToken
}
