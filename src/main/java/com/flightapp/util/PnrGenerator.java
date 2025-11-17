package com.flightapp.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PnrGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generatePnr(String flightNumber) {
        // PN format i have used is first 2 chars of flight + datetime + 4 random chars
        String prefix = flightNumber.replaceAll("[^A-Z0-9]", "").toUpperCase();
        if (prefix.length() > 3) {
            prefix = prefix.substring(0, 3);
        }

        String timePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));
        StringBuilder sb = new StringBuilder(prefix).append(timePart);

        for (int i = 0; i < 4; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    private PnrGenerator() {}
}
