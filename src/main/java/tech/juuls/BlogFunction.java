package tech.juuls;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

public class BlogFunction {
    private static final WebhookService webhookService;

    static {
        String connectionString = System.getenv("SERVICE_BUS_CONNECTION_STRING");
        String queueName = System.getenv("SERVICE_BUS_QUEUE_NAME");
        webhookService = new WebhookService(connectionString, queueName);
    }

    @FunctionName("webhook")
    public HttpResponseMessage webhook(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
            final ExecutionContext context
    ) {
        try {
            webhookService.queueMessage(request.getBody());
            return request.createResponseBuilder(HttpStatus.OK)
                    .body("Message queued successfully")
                    .build();
        } catch (Exception e) {
            context.getLogger().severe("Failed to queue message: " + e.getMessage());
        }

        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to queue message")
                .build();
    }
}
