package com.koszalka.shortener.rest.controllers;

import com.koszalka.shortener.core.bo.ShortenerBO;
import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.rest.api.ShortenerAPI;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController implements ShortenerAPI {

    private final ShortenerBO shortenerBO;
    private static final Logger logger = LoggerFactory.getLogger(ShortenerController.class);


    @Autowired
    public ShortenerController(ShortenerBO shortenerBO) {
        this.shortenerBO = shortenerBO;
    }

    @Override
    public ResponseEntity postNewUrlString(ShortenerDTO dto)  {
        ShortenerEntity entity = new ShortenerEntity();
        entity.setExpirationDate(dto.getExpiresAt());
        entity.setOriginal(dto.getOriginalUrl());
        try {
            if( shortenerBO.saveOne(entity)) {
                return new ResponseEntity(HttpStatus.CREATED);
            }
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Error: " + ex);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



}
