package com.koszalka.shortener.rest.controllers;

import com.koszalka.shortener.constants.AppConstants;
import com.koszalka.shortener.bo.ShortenerBO;
import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.rest.api.ShortenerAPI;
import java.time.Instant;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController implements ShortenerAPI {

    private final ShortenerBO shortenerBO;

    @Autowired
    public ShortenerController(ShortenerBO shortenerBO) {
        this.shortenerBO = shortenerBO;
    }

    @Override
    public void getUrlByString(HttpServletResponse response, String hash) {
        long now = Instant.now().toEpochMilli();
        ShortenerEntity entity = shortenerBO.getUrlFromHash(hash);
        if (now < entity.getExpirationDate()) {
            shortenerBO.send301Redirect(response, entity.getOriginal(), entity.getExpirationDate());
        } else {
            redirectIsGone();
        }
    }

    private ResponseEntity redirectIsGone() {
        return new ResponseEntity(HttpStatus.GONE);
    }

    @Override
    public ResponseEntity<ShortenerDTO> postNewUrlString(ShortenerDTO dto)  {
        ShortenerEntity entity = new ShortenerEntity();
        entity.setExpirationDate(dto.getExpiresAt());
        entity.setOriginal(dto.getOriginalUrl());

        if (shortenerBO.saveOne(entity).equals(AppConstants.BAD_REQUEST.getValue())) {
            return new ResponseEntity<ShortenerDTO>(HttpStatus.BAD_REQUEST); // malformed url
        }

        String hash = shortenerBO.saveOne(entity);
        ShortenerDTO response = new ShortenerDTO(AppConstants.LOCALHOST.getValue() + hash, dto.getExpiresAt());
        return new ResponseEntity<ShortenerDTO>(response, HttpStatus.CREATED);

    }

}
