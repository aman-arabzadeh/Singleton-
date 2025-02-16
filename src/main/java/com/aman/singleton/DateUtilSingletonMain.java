package com.aman.singleton;

import com.aman.singleton.util.DateUtilSingleton;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtilSingletonMain {
    private static final Logger logger = Logger.getLogger(DateUtilSingletonMain.class.getName());
    public static void main(String[] args) {
        List<DateUtilSingleton> dateUtilList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dateUtilList.add(DateUtilSingleton.getInstance());
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("singleton.ser"))) {
            objectOutputStream.writeObject(DateUtilSingleton.getInstance());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fel vid serialisering av objektet", e);
        }

        DateUtilSingleton deserializedDateUtil = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
            deserializedDateUtil = (DateUtilSingleton) objectInputStream.readObject();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fel vid läsning av filen", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Fel: Klassen kunde inte hittas vid deserialisering", e);
        }


        List<String> timestamps = dateUtilList.stream()
                .map(DateUtilSingleton::getCurrentTimestamp)
                .toList();

        System.out.println(".".repeat(timestamps.size() * 3).concat("\n"));
        for (int i = 0; i < timestamps.size(); i++) {
            System.out.println((i + 1) + ": " + timestamps.get(i).indent(2));
        }

        System.out.println(".".repeat(timestamps.size() * 3).concat("\n"));
        boolean allSame = dateUtilList.stream().allMatch(instance -> instance == dateUtilList.get(0));
        System.out.println("Är alla 10 instanser samma? " + allSame);
        System.out.println("Är den deserialiserade instansen samma? " + (dateUtilList.get(0) == deserializedDateUtil));
    }
}