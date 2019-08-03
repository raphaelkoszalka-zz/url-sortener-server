package com.koszalka.shortener.rest.controllers;

import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import com.koszalka.shortener.rest.api.ShortenerAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController implements ShortenerAPI {

    @Override
    public ResponseEntity<ShortenerDTO> getUrlByString(String urlString) {
        return null;
    }

    @Override
    public ResponseEntity<ShortenerDTO> postNewUrlString(String urlString) {
        return null;
    }

}
