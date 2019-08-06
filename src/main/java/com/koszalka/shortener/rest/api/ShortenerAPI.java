package com.koszalka.shortener.rest.api;

import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "/v1")
public interface  ShortenerAPI {

    @RequestMapping(path = "/{urlString}",method = RequestMethod.GET)
    void getUrlByString(HttpServletResponse response, @PathVariable("urlString") String urlString);

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    ResponseEntity<ShortenerDTO> postNewUrlString(@RequestBody ShortenerDTO data);

}

