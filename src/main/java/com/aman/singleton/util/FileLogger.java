package com.aman.singleton.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private final String logFilePath;

    public FileLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void log(String msg) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(msg);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Kunde inte skriva till loggfilen: " + e.getMessage());
        }
    }
}