package be.edu.bggclient.internal;

import java.net.URI;

public enum BggApi {
    V1("https://boardgamegeek.com/xmlapi/"),
    V2("https://boardgamegeek.com/xmlapi2/");

    private final String baseUrl;

    BggApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public UrlFactory create(String subpath) {
        return queryString -> URI.create(baseUrl + subpath + "?" + queryString);
    }
}
