package com.aman.util;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilSingleton implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L; // serialVersionUID för serialisering

    private static volatile DateUtilSingleton instance;
    private final SimpleDateFormat dateFormat;

    private DateUtilSingleton() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


    public static DateUtilSingleton getInstance() {
        if (instance == null) {
            synchronized (DateUtilSingleton.class) {
                if (instance == null) {
                    instance = new DateUtilSingleton();
                }
            }
        }
        return instance;
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }


    public String getCurrentTimestamp() {
        return formatDate(new Date());
    }

    /**
     * Skyddar mot att en ny instans skapas vid serialisering.
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
