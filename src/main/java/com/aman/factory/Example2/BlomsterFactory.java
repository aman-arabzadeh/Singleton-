package com.aman.factory.Example2;

import java.util.Optional;

public class BlomsterFactory {

    private static Optional<AndraBlommor> matchaBlomma(Blommor blomma) {
        return switch (blomma) {
            case ROS -> Optional.of(AndraBlommor.ROS);
            case TULPAN -> Optional.of(AndraBlommor.TULPAN);
            case ORKIDÉ -> Optional.of(AndraBlommor.ORKIDÉ);
            case SOLROS -> Optional.of(AndraBlommor.SOLROS);
            case LILJA -> Optional.of(AndraBlommor.LILJA);
            default -> Optional.of(AndraBlommor.OKAND);
        };
    }

    public static Optional<AndraBlommor> getAndraBlomma(Blommor blomma) {
        if (blomma == null) {
            return Optional.empty();
        }
        return matchaBlomma(blomma);
    }

    private static String hamtaBlommansNamn(Blommor blomma) {
        return matchaBlomma(blomma)
                .map(Enum::name) // Konvertera till sträng
                .orElse("Okänd blomma");
    }

    public static void skrivUtBlomma(Blommor blomma) {
        String blommaNamn = hamtaBlommansNamn(blomma);
        System.out.println("Blommans namn: " + blommaNamn);
    }
}
