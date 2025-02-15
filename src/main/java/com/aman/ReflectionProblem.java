package com.aman;

import com.aman.util.DateUtilSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionProblem {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DateUtilSingleton util = DateUtilSingleton.getInstance();
        DateUtilSingleton util2 = null;

        Constructor<?>[] constructors = DateUtilSingleton.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            try {
                util2 = (DateUtilSingleton) constructor.newInstance();
                break;
            } catch (InstantiationException | IllegalArgumentException | InvocationTargetException e) {
                System.err.println("Kunde inte skapa en ny instans med konstruktorn: " + constructor);
            }
        }

        System.out.println(util.hashCode());
        System.out.println(util2.hashCode());
    }
}
