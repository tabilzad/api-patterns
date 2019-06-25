# api-patterns

# SystemOrchestration 
Originates the orchestration of [MessagingServices](#MessagingService)

# MessagingService
Handles the orchestration of [MessagingFactory](#MessagingFactory), [SystemClient](#SystemClient) and [ResponseService](#ResponseService)

# MessagingFactory
Responsible for producing a message to get sent by [SystemClient](#SystemClient).

# SystemClient
Responsible for making a network exchange with an external resource. Proper error handling should be provided here. 

# ServiceError
Stores the name of the Service the error originated from, message and errorType describing the nature of error. 

# ServiceName

Enumerated list of all existing Messaging Services

# ErrorType
Enumerated list of possible error types