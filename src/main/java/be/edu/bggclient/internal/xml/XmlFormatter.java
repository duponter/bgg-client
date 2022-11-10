package be.edu.bggclient.internal.xml;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;

public final class XmlFormatter {
    private static final DateTimeFormatter BGG_DATE_TIME = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss +0000", Locale.US);
    private static final DateTimeFormatter BGG_ISO_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private XmlFormatter() {
        throw new UnsupportedOperationException("This static class cannot be instantiated.");
    }

    public static LocalDate parseDate(String input) {
        return isNotEmpty(input) ? LocalDate.parse(input) : null;
    }

    public static LocalDateTime parseDateTime(String input) {
        return isNotEmpty(input) ? LocalDateTime.parse(input, BGG_DATE_TIME) : null;
    }

    public static LocalDateTime parseIsoDateTime(String input) {
        return isNotEmpty(input) ? LocalDateTime.parse(input.replace(' ', 'T')) : null;
    }

    public static String format(String input) {
        return input;
    }

    public static String format(int input) {
        return Integer.toString(input);
    }

    public static String format(double input) {
        return input != 0 ? Double.toString(input) : format(0);
    }

    public static String format(boolean input) {
        return input ? "1" : "0";
    }

    public static String format(LocalDate input) {
        return format(input, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String format(LocalDateTime input) {
        return format(input, BGG_DATE_TIME);
    }

    public static String formatIso(LocalDateTime input) {
        return format(input, BGG_ISO_DATE_TIME);
    }

    private static String format(Temporal input, DateTimeFormatter formatter) {
        return input != null ? (formatter.format(input)) : "";
    }

    private static boolean isNotEmpty(String input) {
        return input != null && !input.isEmpty();
    }
}
