package com.results

sealed class Result<out SUCCESS, out FAILURE>

inline fun <SUCCESS, NEW_SUCCESS, FAILURE> Result<SUCCESS, FAILURE>.map(transform: (SUCCESS) -> NEW_SUCCESS)
        : Result<NEW_SUCCESS, FAILURE> {
    return when (this) {
        is Success -> Success(transform(this.value))
        is Failure -> this
    }
}

inline fun <SUCCESS, FAILURE, NEW_FAILURE> Result<SUCCESS, FAILURE>.mapFailure(transform: (FAILURE) -> NEW_FAILURE): Result<SUCCESS, NEW_FAILURE> {
    return when (this) {
        is Success -> this
        is Failure -> Failure(transform(this.value))
    }
}

inline fun <SUCCESS, FAILURE> Result<SUCCESS, FAILURE>.getOrElse(transform: (FAILURE) -> SUCCESS): SUCCESS {
    return when (this) {
        is Success -> value
        is Failure -> transform(this.value)
    }
}

inline fun <SUCCESS, NEW_SUCCESS, FAILURE> Result<SUCCESS, FAILURE>.flatMap(transform: (SUCCESS) -> Result<NEW_SUCCESS, FAILURE>): Result<NEW_SUCCESS, FAILURE> {
    return when (this) {
        is Success -> transform(this.value)
        is Failure -> this
    }
}

data class Success<out SUCCESS>(val value: SUCCESS) : Result<SUCCESS, Nothing>()
data class Failure<out FAILURE>(val value: FAILURE) : Result<Nothing, FAILURE>()