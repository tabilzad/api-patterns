package com.timabil.services

import com.results.Result
import com.results.map
import com.timabil.interfaces.DataBag
import com.timabil.interfaces.MessageFactory
import com.timabil.interfaces.Transformer

abstract class MessagingService<in INPUT, MESSAGE, RESPONSE, SUCCESS, FAILURE, DATA_BAG : DataBag>(
    open val client: SystemClient<MESSAGE, RESPONSE, FAILURE>,
    open val responseService: Transformer<RESPONSE, SUCCESS>,
    open val factory: MessageFactory<INPUT, MESSAGE, DATA_BAG>
) {

    open fun send(input: INPUT, dataBag: DATA_BAG): Result<SUCCESS, FAILURE> {
        return factory.make(input, dataBag).let { message ->
            client.send(message).map { success ->
                responseService.transform(success)
            }
        }
    }
}