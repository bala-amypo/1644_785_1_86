package com.example.demo.util;

public class ValidationUtil {
    public static boolean validSeason(String s) {
        return s != null && (s.equals("Kharif") || s.equals("Rabi") || s.equals("Zaid"));
    }
}
