package com.aman.factory.Example2;

import java.util.List;
import java.util.Random;

public class Main {

    private static final List<AndraBlommor> VALUES =
            List.of(AndraBlommor.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static AndraBlommor randomBlomma() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public static void main(String[] args) {
        BlomsterFactory.getAndraBlomma(Blommor.ROS)
                .ifPresent(b -> System.out.println("Blomman är: " + b));

        AndraBlommor blomma1 = BlomsterFactory.getAndraBlomma(null).orElse(AndraBlommor.OKAND);
        System.out.println("Standardvärde används: " + blomma1);

        AndraBlommor blomma2 = BlomsterFactory.getAndraBlomma(null)
                .orElseGet(() -> {
                    System.out.println("Väljer en slumpmässig blomma...");
                    return randomBlomma();
                });
        System.out.println("Alternativ blomma: " + blomma2);

        try {
            AndraBlommor blomma3 = BlomsterFactory.getAndraBlomma(null)
                    .orElseThrow(() -> new IllegalArgumentException("Ogiltig blomma!"));
            System.out.println("Blomman är: " + blomma3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String blommaNamn = BlomsterFactory.getAndraBlomma(Blommor.TULPAN)
                .map(Enum::name)
                .orElse("Okänd blomma");
        System.out.println("Blommans namn: " + blommaNamn);
    }
}