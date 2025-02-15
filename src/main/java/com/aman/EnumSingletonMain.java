package com.aman;

import com.aman.util.DateUtilEnumSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingletonMain {
    public static void main(String[] args) {
        DateUtilEnumSingleton instance1 = DateUtilEnumSingleton.INSTANCE;

        try {
            Constructor<?>[] constructors = DateUtilEnumSingleton.class.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                constructor.setAccessible(true);
                DateUtilEnumSingleton instance2  = (DateUtilEnumSingleton) constructor.newInstance();
                System.out.println("Kollar om samma hashcode: " + (instance1.hashCode() == instance2.hashCode() ? "True" : "False"));

            }
        } catch (InstantiationException | IllegalAccessException  | InvocationTargetException  e) {
            System.err.println(" Misslyckades att skapa en ny instans via reflektion: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Annat fel inträffade: " + e.getMessage());
        }

        System.out.println("Instance 1 hashcode: " + instance1.hashCode());

        System.out.println("-".repeat(42).concat("\n"));

        System.out.println(instance1.getCurrentTimestamp());
        System.out.println("Instance 1 datum: " + instance1.getCurrentTimestamp());
        System.out.println("hash koden instance 1: " + instance1.hashCode());
    }
}
