package com.school.midland.admin.client.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

public class ClientExceptionResponse {

    public static String extractErrorMessageFromResponse(String responseBody) {
        try {

            JsonNode rootNode = new ObjectMapper().readTree(responseBody);
            return rootNode.path("message").asText("Unknown error occurred");
        } catch (JsonProcessingException e) {
            return "Failed to parse error response from student service";
        }
    }

    // Utility method to extract the error status code from response body
    public  static HttpStatus extractErrorStatusFromResponse(String responseBody) {
        try {
            // Again assuming the response is in JSON format
            JsonNode rootNode = new ObjectMapper().readTree(responseBody);
            int statusCode = rootNode.path("status").asInt(500); // Default to 500 if not present
            return HttpStatus.valueOf(statusCode);
        } catch (JsonProcessingException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR; // Default status if parsing fails

        }
    }
}
