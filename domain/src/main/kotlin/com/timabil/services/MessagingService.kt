package com.timabil.services

import com.results.Result
import com.results.map
import com.timabil.interfaces.DataBag
import com.timabil.interfaces.MessageFactory
import com.timabil.interfaces.Messenger
import com.timabil.interfaces.ResponseService

abstract class MessagingService<in INPUT, MESSAGE, RESPONSE, SUCCESS, FAILURE, DATA_BAG : DataBag>(
    open val client: SystemClient<MESSAGE, RESPONSE, FAILURE>,
    open val responseService: ResponseService<RESPONSE, SUCCESS>,
    open val factory: MessageFactory<INPUT, MESSAGE, DATA_BAG>
) {

    open fun send(input: INPUT, dataBag: DATA_BAG): Result<SUCCESS, FAILURE> {
        return factory.make(input, dataBag).let { message ->
            client.send(message).map { success ->
                responseService.toWire(success)
            }
        }
    }
}

abstract class MessagingService2<REQUEST, MESSAGE, OUTPUT, RESPONSE, DATABAG : DataBag>(
    private val factory: MessageFactory<REQUEST, MESSAGE, DATABAG>,
    private val client: SystemClient2<MESSAGE, RESPONSE>,
    private val responseService: ResponseService<RESPONSE, OUTPUT>
) : Messenger<REQUEST, DATABAG, OUTPUT> {
    override fun send(message: REQUEST, databag: DATABAG): OUTPUT {
        return factory.make(message, databag).let { request ->
            client.send(request, databag.token).let { response ->
                responseService.toWire(response)
            }
        }
    }
}