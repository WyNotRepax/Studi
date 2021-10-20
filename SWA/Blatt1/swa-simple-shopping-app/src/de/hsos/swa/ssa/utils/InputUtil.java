package de.hsos.swa.ssa.utils;

import java.util.Scanner;

import de.hsos.swa.ssa.utils.Geld.Waehrung;

public class InputUtil {

    private static Scanner scanner;

    private static synchronized Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static int getInt() {
        try {
            return Integer.parseInt(getScanner().nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long getLong() {
        try {
            return Long.parseLong(getScanner().nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getString() {
        return getScanner().nextLine();
    }

    public static Geld getGeld() {
        String line = getScanner().nextLine();
        if (line.length() == 0) {
            return null;
        }
        char lastChar = line.charAt(line.length() - 1);
        Waehrung waehrung = null;
        for (Waehrung w : Waehrung.values()) {
            if (w.displayChar == lastChar) {
                waehrung = w;
                break;
            }
        }
        if (waehrung == null) {
            return null;
        }
        line = line.substring(0, line.length() - 1);
        double value = 0;
        try {
            value = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            return null;
        }
        return new Geld(value, waehrung);
    }

    public static boolean getBoolean(){
        String line = getString();
        return line.toLowerCase().equals("y");
    }
}
