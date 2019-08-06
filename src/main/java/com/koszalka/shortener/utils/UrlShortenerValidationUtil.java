package com.koszalka.shortener.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlShortenerValidationUtil {

    private String url;
    private static final String REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public UrlShortenerValidationUtil(String url) {
        this.url = url;
    }

    public boolean validateURL() {
        Matcher m = PATTERN.matcher(url);
        return m.matches();
    }

}
