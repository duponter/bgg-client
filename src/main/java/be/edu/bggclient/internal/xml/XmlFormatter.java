package be.edu.bggclient.internal.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class XmlFormatter {
    private static final DateTimeFormatter BGG_DATE_TIME = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss +0000", Locale.US);

    private XmlFormatter() {
        throw new UnsupportedOperationException("This static class cannot be instantiated.");
    }

    public static LocalDateTime parseDateTime(String input) {
        return LocalDateTime.parse(input, BGG_DATE_TIME);
    }

    public static String format(String input) {
        return input;
    }

    public static String format(int input) {
        return Integer.toString(input);
    }

    public static String format(LocalDateTime input) {
        return input.format(BGG_DATE_TIME);
    }
}
