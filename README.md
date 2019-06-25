# api-patterns

# SystemOrchestration 
Originates the orchestration of MessagingServices (#MessagingService)

# MessagingService
Handles the orchestration of MessagingFactory, SystemClient and ResponseService

# MessagingFactory
Responsible for producing a message to get sent by SystemClient.

# SystemClient
Responsible for making a network exchange with an external resource. Proper error handling should be provided here. 

# ServiceError
Class definition of 

# ServiceName

# ErrorType