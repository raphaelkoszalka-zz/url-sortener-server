package com.koszalka.shortener.rest.controllers;

import com.koszalka.shortener.core.bo.ShortenerBO;
import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.rest.api.RedirectAPI;
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
        if (now > entity.getExpirationDate()) {
            shortenerBO.send301Redirect(response, entity.getOriginal());
        } else {
            redirectIsGone();
        }
    }

    private ResponseEntity redirectIsGone() {
        return new ResponseEntity(HttpStatus.GONE);
    }

    @Override
    public ResponseEntity postNewUrlString(ShortenerDTO dto)  {
        ShortenerEntity entity = new ShortenerEntity();
        entity.setExpirationDate(dto.getExpiresAt());
        entity.setOriginal(dto.getOriginalUrl());
        if(shortenerBO.saveOne(entity).equals("saved")) {
            // redirect id saved with success
            return new ResponseEntity(HttpStatus.CREATED);
        } else if (shortenerBO.saveOne(entity).equals("exists")) {
            // redirect id already exists on database
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        // url invalid
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
