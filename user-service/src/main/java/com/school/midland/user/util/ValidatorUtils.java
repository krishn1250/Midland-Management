package com.school.midland.user.util;

import com.school.midland.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

public final class ValidatorUtils {

    private ValidatorUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validate that an object is not null.
     */
    public static void validateObject(Object obj, String name) {
        if (obj == null) {
            throw new UserException(name + " cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Validate that a string field has text (not null / empty / whitespace).
     */
    public static void validateField(String value, String fieldName) {
        if (!StringUtils.hasText(value)) {
            throw new UserException(fieldName + " is required", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Validate that id is not null and positive.
     */
    public static void validateId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new UserException(fieldName + " must be a positive id", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Null-safe check for empty collections.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Validate pagination parameters (page >= 0, size > 0).
     */
    public static void validatePageParams(int page, int size) {
        if (page < 0) {
            throw new UserException("Page index must be >= 0", HttpStatus.BAD_REQUEST);
        }
        if (size <= 0) {
            throw new UserException("Page size must be > 0", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Validate boolean expression, throw UserException with provided message/status when false.
     */
    public static void assertTrue(boolean condition, String message, HttpStatus status) {
        if (!condition) {
            throw new UserException(message, status);
        }
    }

    /**
     * Null-safe equality check (helper).
     */
    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }
    public void validateNonEmpty(String field, String fieldName) {
        if (StringUtils.isEmpty(field)) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    public void validateNonNull(Object field, String fieldName) {
        if (field == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public void validateLength(String field, String fieldName, int maxLength) {
        if (field != null && field.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " exceeds maximum length of " + maxLength);
        }
    }
}
