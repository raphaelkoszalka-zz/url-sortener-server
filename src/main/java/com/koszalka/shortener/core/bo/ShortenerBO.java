package com.koszalka.shortener.core.bo;

import com.koszalka.shortener.utils.UrlShortenerShuffle;
import com.koszalka.shortener.utils.UrlShortenerUtil;
import com.koszalka.shortener.utils.UrlShortenerValidationUtil;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.persistence.repositories.ShortenerRepository;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenerBO {

    private final ShortenerRepository shortenerRepository;
    private final UrlShortenerShuffle urlShortenerShuffle;

    @Autowired
    public ShortenerBO(ShortenerRepository shortenerRepository, UrlShortenerShuffle urlShortenerShuffle) {
        this.shortenerRepository = shortenerRepository;
        this.urlShortenerShuffle = urlShortenerShuffle;
    }

    public boolean saveOne(ShortenerEntity entity) throws NoSuchAlgorithmException {
        String hash = "";

        if (verifyCollision(hash) > 0) {
            if (validateURL(entity.getOriginal())) {
                hash = shortenURL(entity.getOriginal());
                entity.setNewUrl(hash);
                shortenerRepository.save(entity);
                return true;
            } else {
                return false;
            }
        }

        entity.setNewUrl(urlShortenerShuffle.shuffle(hash));
        shuffleHash(entity);
        return true;
    }

    private void shuffleHash(ShortenerEntity entity) {
        shortenerRepository.save(entity);
    }

    public String getUrlFromHash(String hash) {
        return shortenerRepository.getOriginalUrlFromHash(hash);
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
