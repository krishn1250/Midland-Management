package com.school.midland.utils;



import java.util.UUID;

public class UuidGenerator {

    private UuidGenerator() {
        // Prevent instantiation
    }

    /**
     * Generates a random UUID (version 4) safely.
     */
    public static UUID generate() {
        return UUID.randomUUID();
    }

    /**
     * Generates a UUID as a string.
     */
    public static String generateString() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a UUID and retries if collision occurs (rarely needed).
     * You can call this from your service and retry insert if needed.
     */
    public static UUID generateWithRetry(UuidInsertHandler handler, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            UUID uuid = UUID.randomUUID();
            if (handler.tryInsert(uuid)) {
                return uuid;
            }
            attempts++;
        }
        throw new RuntimeException("Unable to generate unique UUID after " + maxRetries + " attempts");
    }

    // Functional interface for insert callback
    @FunctionalInterface
    public interface UuidInsertHandler {
        boolean tryInsert(UUID uuid); // return true if insert succeeds
    }
}
