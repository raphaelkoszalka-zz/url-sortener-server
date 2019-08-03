package com.koszalka.shortener.core.bo;

import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.persistence.repositories.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenerBO {

    private final ShortenerRepository shortenerRepository;

    @Autowired
    public ShortenerBO(ShortenerRepository shortenerRepository) {
        this.shortenerRepository = shortenerRepository;
    }

    public void saveOne(ShortenerEntity entity) {
        shortenerRepository.save(entity);
    }



}
