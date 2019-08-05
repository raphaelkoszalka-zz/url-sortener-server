package com.koszalka.shortener.rest.api;

import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "/short-url")
public interface  ShortenerAPI {

    @RequestMapping(path = "", method = RequestMethod.POST)
    ResponseEntity postNewUrlString(@RequestBody ShortenerDTO data);

}

