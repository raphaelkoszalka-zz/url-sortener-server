package com.koszalka.shortener.core.bo;

import com.koszalka.shortener.core.utils.UrlShortenerUtil;
import com.koszalka.shortener.core.utils.UrlShortenerValidationUtil;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.persistence.repositories.ShortenerRepository;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenerBO {

    private final ShortenerRepository shortenerRepository;


    @Autowired
    public ShortenerBO(ShortenerRepository shortenerRepository) {
        this.shortenerRepository = shortenerRepository;
    }

    public boolean saveOne(ShortenerEntity entity) throws NoSuchAlgorithmException {
        try {
            if (validateURL(entity.getOriginal())) {
                entity.setNewUrl(shortenURL(entity.getOriginal()));
                shortenerRepository.save(entity);
                return true;
            }
            return false;
        } catch(NoSuchAlgorithmException ex) {
            throw ex;
        }
    }

    private String shortenURL(String url) throws NoSuchAlgorithmException {
        UrlShortenerUtil shortener = new UrlShortenerUtil(url);
        return shortener.converStringToHash(url.getBytes());

    }

    private boolean validateURL(String url) {
        UrlShortenerValidationUtil shortener = new UrlShortenerValidationUtil(url);
        return shortener.validateURL();
    }



}
