package com.koszalka.shortener.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

public class UrlShortenerUtil {

    byte[] dataBytes;

    public UrlShortenerUtil(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public String converStringToHash() throws NoSuchAlgorithmException {
        if(dataBytes == null) return "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(dataBytes);
        byte[] digest = md.digest();

        BigInteger bi = new BigInteger(digest);
        String s = bi.toString(16);
        if( s.length() %2 != 0) {
            s = "0"+s;
        }
        return s;
    }


}
