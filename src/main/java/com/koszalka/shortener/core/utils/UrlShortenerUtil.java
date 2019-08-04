package com.koszalka.shortener.core.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UrlShortenerUtil {

    private String url;

    public UrlShortenerUtil(String url) {
        this.url = url;
    }

    public String converStringToHash(byte[] dataBytes) throws NoSuchAlgorithmException {
        if( dataBytes == null) return "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(dataBytes);
        byte[] digest = md.digest();

        // convert it to the hexadecimal
        BigInteger bi = new BigInteger(digest);
        String s = bi.toString(16);
        if( s.length() %2 != 0)
        {
            s = "0"+s;
        }
        return s;
    }


}
