package com.timabil.interfaces

import com.results.Result

interface SystemError<NAME, ERROR_TYPE>

interface DataBag
object NullDataBag : DataBag

interface SystemOrchestration<SUCCESS, FAILURE, REQUEST> {
    fun orchestrate(request: REQUEST): Result<SUCCESS, FAILURE>
}

interface MessageFactory<in INPUT, out MESSAGE, in DATA_BAG : DataBag> {
    fun make(intput: INPUT, databag: DATA_BAG): MESSAGE
}

interface Transformer<in INPUT, out OUTPUT> {
    fun transform(from: INPUT): OUTPUT
}

interface ErrorHandler<SUCCESS, FAILURE>

interface RestErrorHandler<SUCCESS, FAILURE> : ErrorHandler<SUCCESS, FAILURE> {
    operator fun invoke(call: () -> Any): Result<SUCCESS, FAILURE>
}