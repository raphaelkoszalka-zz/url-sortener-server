package com.koszalka.shortener.utils;

public class UrlShortenerUtil {

    private static final String STRING_MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public UrlShortenerUtil() {}

    public String idToUrl(int id) {
        StringBuilder tinyUrl = new StringBuilder();
        while(id>0) {
            tinyUrl.append(STRING_MAP.charAt(id%62));
            id /= 62;
        }
        return tinyUrl.reverse().toString();
    }

    public int urlToId(String tinyUrl) {
        int id = 0;

        for(int i=0;i<tinyUrl.length();i++) {
            id = (id*62) + STRING_MAP.indexOf(tinyUrl.charAt(i));
        }

        if (id < 0) {
            id = id * -1;
        }

        return id;
    }


}
