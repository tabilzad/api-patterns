package com.timabil.model

import com.timabil.interfaces.SystemError

data class ServiceError(
    val service: ServiceName,
    val message: String,
    val errorType: ErrorType
) : SystemError<ServiceName, ErrorType>

enum class ServiceName(val value: String) {
    //Add services here
}

enum class ErrorType {
    //Add Error Types here
}