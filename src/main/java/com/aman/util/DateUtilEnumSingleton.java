package com.aman.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Singleton-klass som använder Enum för att säkerställa:
 * - Thread-safety
 * - Serialization-skydd
 * - Skydd mot reflektion och kloning
 *
 */
public enum  DateUtilEnumSingleton {
    INSTANCE;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public String getCurrentTimestamp() {
        return formatDate(new Date());
    }

    private static DateUtilEnumSingleton getInstance() {
        return INSTANCE;
    }
}
