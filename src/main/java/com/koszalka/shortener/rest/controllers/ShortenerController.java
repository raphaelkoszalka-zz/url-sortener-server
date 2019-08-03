package com.koszalka.shortener.rest.controllers;

import com.koszalka.shortener.core.bo.ShortenerBO;
import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import com.koszalka.shortener.persistence.dto.ShortenerPostResponseDTO;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.rest.api.ShortenerAPI;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController implements ShortenerAPI {

    private final ShortenerBO shortenerBO;

    @Autowired
    public ShortenerController(ShortenerBO shortenerBO) {
        this.shortenerBO = shortenerBO;
    }

    @Override
    public ResponseEntity<ShortenerDTO> getUrlByString(String urlString) {
        return null;
    }

    @Override
    public ResponseEntity postNewUrlString(ShortenerDTO dto) {

        ShortenerEntity entity = new ShortenerEntity();
        entity.setExpirationDate(LocalDateTime.now());
        entity.setNewUrl(dto.getNewUrl());
        entity.setOriginal(dto.getOriginalUrl());

        shortenerBO.saveOne(entity);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
