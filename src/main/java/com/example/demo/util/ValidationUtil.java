
package com.example.demo.util;

import java.util.Set;

public class ValidationUtil {
    private static final Set<String> SEASONS = Set.of("Kharif", "Rabi");

    public static boolean validSeason(String s) {
        return SEASONS.contains(s);
    }
}
