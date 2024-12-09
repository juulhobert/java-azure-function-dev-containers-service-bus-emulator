package tech.juuls;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

import java.io.Closeable;

public class WebhookService implements Closeable {
    private final ServiceBusSenderClient senderClient;

    public WebhookService(String connectionString, String queueName) {
        senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }

    public void queueMessage(String message) {
        senderClient.sendMessage(new ServiceBusMessage(message));
    }

    @Override
    public void close() {
        senderClient.close();
    }
}
