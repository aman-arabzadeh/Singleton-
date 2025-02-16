package com.aman.singleton;

import com.aman.singleton.util.LoggerSingleton;

import java.io.*;

public class LoggerSingletonTest {
    public static void main(String[] args) {
        LoggerSingleton logger = LoggerSingleton.getInstance();

        testSerialization(logger);
    }


    /**
     * Testar serialization och deserialization av LoggerSingleton
     * och validerar att det fortfarande är samma instans.
     *
     * @param logger LoggerSingleton-instansen att serialisera.
     */
    private static void testSerialization(LoggerSingleton logger) {
        final String fileName = "logger.ser";

        // Serialization
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(logger);
            logger.logInfo("Serialization successful. Logger saved to " + fileName);
        } catch (IOException e) {
            logger.logError("Error during serialization: " + e.getMessage());
            return;
        }

        // Deserialization
        LoggerSingleton deserializedLogger;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            deserializedLogger = (LoggerSingleton) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.logError("Error during deserialization: " + e.getMessage());
            return;
        }

        if (deserializedLogger != null) {
            if (logger.hashCode() == deserializedLogger.hashCode()) {
                logger.logInfo("Serialization Test Passed: Same instance after deserialization.");
            } else {
                logger.logError("Serialization Test Failed: Different instances created after deserialization.");
            }
        }
    }
}