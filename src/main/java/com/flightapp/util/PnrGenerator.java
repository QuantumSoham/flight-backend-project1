package com.flightapp.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PnrGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ0123456789";

    // new signature: also take seatSignature
    public static String generatePnr(String flightNumber, String seatSignature) {
        // ----- 1) your existing base PNR logic -----
        String prefix = flightNumber.replaceAll("[^A-Z0-9]", "").toUpperCase();
        if (prefix.length() > 3) {
            prefix = prefix.substring(0, 3);
        }

        String timePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));

        StringBuilder sb = new StringBuilder(prefix).append(timePart);

        for (int i = 0; i < 4; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        String basePnr = sb.toString();

        // ----- 2) hash basePNR + seatSignature to get a short suffix -----
        String toHash = basePnr + ":" + (seatSignature == null ? "" : seatSignature);
        String hashSuffix = shortHash(toHash, 3); // 3-char hash tail

        // ----- 3) final PNR = base + hash tail -----
        return basePnr + hashSuffix;
    }

    // helper: compute SHA-256 and turn first bits into ALPHABET chars
    private static String shortHash(String input, int length) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();
            int bits = 0;
            int value = 0;

            for (byte b : hash) {
                value = (value << 8) | (b & 0xFF);
                bits += 8;
                while (bits >= 5 && result.length() < length) {
                    int idx = (value >> (bits - 5)) & 0b1_1111; // 0–31
                    bits -= 5;
                    result.append(ALPHABET.charAt(idx % ALPHABET.length()));
                }
                if (result.length() == length) break;
            }

            // safety: if still short, pad with random chars
            while (result.length() < length) {
                result.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            }

            return result.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate PNR hash", e);
        }
    }

    private PnrGenerator() {}
}
