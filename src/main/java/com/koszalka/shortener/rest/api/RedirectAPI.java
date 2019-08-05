package com.koszalka.shortener.rest.api;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "")
public interface  RedirectAPI {

    @RequestMapping(path = "/{urlString}",method = RequestMethod.GET)
    void getUrlByString(HttpServletResponse response, @PathVariable("urlString") String urlString);

}

