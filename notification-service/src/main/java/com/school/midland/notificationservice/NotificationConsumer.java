// NotificationConsumer.java (new file)
package com.school.midland.notificationservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @KafkaListener(topics = "school-events-topic", groupId = "notification-group")
    public void consumeEvent(String message) {
        // Handle notification logic, e.g., send email
        System.out.println("Received event for notification: " + message);
        // Integrate with email service or SMS gateway here
    }
}
