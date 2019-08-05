package com.koszalka.shortener.core.bo;

import com.koszalka.shortener.core.utils.UrlShortenerUtil;
import com.koszalka.shortener.core.utils.UrlShortenerValidationUtil;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.persistence.repositories.ShortenerRepository;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenerBO {

    private final ShortenerRepository shortenerRepository;

    @Autowired
    public ShortenerBO(ShortenerRepository shortenerRepository) {
        this.shortenerRepository = shortenerRepository;
    }

    public boolean saveOne(ShortenerEntity entity, boolean firstTime) throws NoSuchAlgorithmException {
        String hash = "";
        if (validateURL(entity.getOriginal()) && firstTime) {
            hash = shortenURL(entity.getOriginal());
            entity.setNewUrl(hash);
            shortenerRepository.save(entity);
            return true;
        }
        if (verifyCollision(hash) == 0) {
            entity.setNewUrl(hash);
            shortenerRepository.save(entity);
            return true;
        } else {
            entity.setNewUrl(sortString(hash));
            saveOne(entity, false);
        }
        return false;
    }

    public String getUrlFromHash(String hash) {
        return shortenerRepository.getOriginalUrlFromHash(hash);
    }

    private String sortString(String inputString) {
        Character tempArray[] = new Character[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            tempArray[i] = inputString.charAt(i);
        }
        Arrays.sort(tempArray, new Comparator<Character>(){
            @Override
            public int compare(Character c1, Character c2) {
                return Character.compare(Character.toLowerCase(c1), Character.toLowerCase(c2));
            }
        });
        StringBuilder sb = new StringBuilder(tempArray.length);
        for (Character c : tempArray) {
            sb.append(c.charValue());
        }
        String test = sb.toString();
        return sb.toString();
    }

    private String shortenURL(String url) throws NoSuchAlgorithmException {
        UrlShortenerUtil shortener = new UrlShortenerUtil(url.getBytes());
        return shortener.converStringToHash();
    }

    private boolean validateURL(String url) {
        UrlShortenerValidationUtil shortener = new UrlShortenerValidationUtil(url);
        return shortener.validateURL();
    }

    private Long verifyCollision(String hash) {
        return shortenerRepository.verifyCollision(hash);
    }

    public void send301Redirect(HttpServletResponse response, String newUrl) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", newUrl);
        response.setHeader("Connection", "close");
    }
}
