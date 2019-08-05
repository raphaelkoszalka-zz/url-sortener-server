package com.koszalka.shortener.rest.controllers;

import com.koszalka.shortener.core.bo.ShortenerBO;
import com.koszalka.shortener.rest.api.RedirectAPI;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectControlller implements RedirectAPI {

    private final ShortenerBO shortenerBO;

    @Autowired
    public RedirectControlller(ShortenerBO shortenerBO) {
        this.shortenerBO = shortenerBO;
    }

    @Override
    public void getUrlByString(HttpServletResponse response, String hash) {
        shortenerBO.send301Redirect(response, shortenerBO.getUrlFromHash(hash));
    }
}
