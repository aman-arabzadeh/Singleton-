package com.aman.util;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerSingleton implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile LoggerSingleton instance;
    private final SimpleDateFormat dateFormat;

    private LoggerSingleton() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static LoggerSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggerSingleton.class) {
                if (instance == null) {
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
    }

    private String getCurrentTimestamp() {
        return dateFormat.format(new Date());
    }

    public void logInfo(String message) {
        System.out.println(getCurrentTimestamp() + " [INFO] " + message);
    }

    public void logWarning(String message) {
        System.out.println(getCurrentTimestamp() + " [WARNING] " + message);
    }

    public void logError(String message) {
        System.err.println(getCurrentTimestamp() + " [ERROR] " + message);
    }

    @Serial
    private Object readResolve() {
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Kloning av denna singleton är inte tillåten.");
    }
}
