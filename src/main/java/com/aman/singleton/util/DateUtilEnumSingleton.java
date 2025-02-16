package com.aman.singleton.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton-klass som använder Enum för att säkerställa:
 * - Thread-safety
 * - Serialization-skydd
 * - Skydd mot reflektion och kloning
 */
public enum DateUtilEnumSingleton {
    INSTANCE;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String formatDate(LocalDateTime date) {
        return date.format(formatter);
    }

    public String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
}