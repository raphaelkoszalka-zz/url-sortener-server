package com.koszalka.shortener.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ShortenerDTO {

    private String newUrl;
    private String expiresAt;

    public ShortenerDTO(String newUrl, String expiresAt) {
        this.newUrl = newUrl;
        this.expiresAt = expiresAt;
    }

}
