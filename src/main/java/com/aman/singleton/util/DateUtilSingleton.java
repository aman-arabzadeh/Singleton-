package com.aman.singleton.util;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Singleton-klass för att hantera datumformattering och tidsstämplar.
 */
public class DateUtilSingleton implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile DateUtilSingleton instance;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // För att blockera instansiering via reflection
    private static boolean isInstanceCreated = false;

    /**
     * Privat konstruktor för att förhindra nya instanser.
     */
    private DateUtilSingleton() {
        if (isInstanceCreated) {
            throw new IllegalStateException("Instans av DateUtilSingleton är redan skapad!");
        }
        isInstanceCreated = true;
    }

    /**
     * Hämtar Singleton-instansen av klassen.
     *
     * @return Singleton-instansen.
     */
    public static DateUtilSingleton getInstance() {
        if (instance == null) {
            synchronized (DateUtilSingleton.class) { // Tråd-säkerhet
                if (instance == null) {
                    instance = new DateUtilSingleton();
                }
            }
        }
        return instance;
    }


    public String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * Säkerställer att enbart Singleton-instansen returneras vid deserialisering.
     *
     * @return Singleton-instansen.
     */
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    /**
     * Förhindrar kloning av Singleton-instansen.
     *
     * @throws CloneNotSupportedException Om kloning försöks.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton-klassen kan inte klonas");
    }
}