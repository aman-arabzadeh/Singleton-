package com.aman;

import com.aman.util.LoggerSingleton;

import java.io.*;

public class LoggerSingletonTest {
    public static void main(String[] args) {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.logInfo("Hello World");

        LoggerSingleton logger1 = LoggerSingleton.getInstance();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("logger.ser"))) {

            out.writeObject(logger1);

        } catch (IOException e) {
            logger.logError("Error during serialization: " + e.getMessage());
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("logger.ser"))) {
            LoggerSingleton loggerSingleton = (LoggerSingleton) in.readObject();

            if (logger1 == loggerSingleton) {
                logger.logInfo("Serialization Test Passed: Same instance after deserialization.");
            } else {
                logger.logError("Serialization Test Failed: Different instances created.");
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.logError("Exception occurred during deserialization: " + e.getMessage());
        }
    }
}
