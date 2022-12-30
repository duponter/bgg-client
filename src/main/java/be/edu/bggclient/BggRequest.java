package be.edu.bggclient;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BggRequest {
    private final Map<String, String> options = new HashMap<>();

    protected void addOption(String name, int value) {
        this.addOption(name, Integer.toString(value));
    }

    protected void addOption(String name, String value) {
        this.options.put(name, URLEncoder.encode(value, Charset.defaultCharset()));
    }

    protected void enableOption(String name) {
        this.addOption(name, "1");
    }

    public String buildQueryString() {
        return this.options.entrySet().stream()
                .map(Object::toString)
                .collect(Collectors.joining("&"));
    }
}
