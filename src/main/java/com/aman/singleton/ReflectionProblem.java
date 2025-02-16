package com.aman.singleton;

import com.aman.singleton.util.DateUtilSingleton;
import com.aman.singleton.util.LoggerSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionProblem {
    public static void main(String[] args) {
        LoggerSingleton loggerSingleton = LoggerSingleton.getInstance();
        try {
            DateUtilSingleton util = DateUtilSingleton.getInstance();
            System.out.println("Original Singleton hashcode: " + util.hashCode());

            Constructor<DateUtilSingleton> constructor = DateUtilSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);

            DateUtilSingleton util2 = constructor.newInstance();

            System.out.println("Reflection Singleton hashcode: " + util2.hashCode());
        } catch (InvocationTargetException e) {
            System.err.println("Reflection-blockerad: " + e.getCause().getMessage());
        }catch (Exception e) {
            loggerSingleton.logError("Ett oväntat fel inträffade: " + e.getMessage());
        }
    }
}