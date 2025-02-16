package com.aman.singleton.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton-klass för loggning med stöd för injektion av anpassad Logger.
 */
public class LoggerSingleton implements Logger, Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile LoggerSingleton instance;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static boolean isInstanceCreated = false;


    /**
     * Privat konstruktor.
     */
    private LoggerSingleton() {
        if (isInstanceCreated) {
            throw new IllegalStateException("Instansen av LoggerSingleton har redan skapats!");
        }
        isInstanceCreated = true;
    }

    /**
     * Returnerar Singleton-instansen. Trådsäker.
     *
     * @return Singleton-instansen.
     */
    public static LoggerSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggerSingleton.class) { // Trådsäker initiering
                if (instance == null) {
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
    }


    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Loggar ett meddelande med angiven nivå via injicerad Logger.
     *
     * @param level   Loggningsnivå (INFO, WARNING, ERROR).
     * @param message Själva loggmeddelandet.
     */
    private void logMessage(String level, String message) {
        String formattedMessage = getCurrentTimestamp() + " [" + level + "] " + message;
        log(formattedMessage);
    }

    public void logInfo(String message) {
        logMessage("[INFO]", message);
        System.out.println("[INFO]" + message);
    }

    public void logWarning(String message) {
        logMessage("[WARNING]", message);
        System.out.println("[WARNING]" + message);
    }

    public void logError(String message) {
        logMessage("[ERROR]", message);
        System.err.println("[ERROR]" + message);
    }

    @Override
    public void log(String msg) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logFileServer.log", true))) {
            writer.write(msg);
            writer.newLine();
        } catch (IOException ex) {
            System.err.println("Fel när loggfilen skulle skrivas: " + ex.getMessage());
        }
    }


    /**
     * Säkerställer att deserialisering inte skapar en ny instans.
     *
     * @return Singleton-instansen.
     */
    @Serial
    private Object readResolve() {
        return instance;
    }

    /**
     * Förhindrar kloning av Singleton-instansen.
     *
     * @throws CloneNotSupportedException Om kloning försöks.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Kloning av denna Singleton är inte tillåten.");
    }
}