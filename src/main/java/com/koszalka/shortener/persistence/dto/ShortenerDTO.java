package com.koszalka.shortener.persistence.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenerDTO {

    private String originalUrl;
    private String newUrl;
    private LocalDateTime expiresAt;

    public ShortenerDTO(String newUrl, String originalUrl, LocalDateTime expiresAt) {
        this.newUrl = newUrl;
        this.expiresAt = expiresAt;
        this.originalUrl = originalUrl;
    }


}
