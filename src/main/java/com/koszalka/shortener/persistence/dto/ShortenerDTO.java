package com.koszalka.shortener.persistence.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortenerDTO {

    private String originalUrl;
    // epoch time, we could use LocalDateTime also, but i'm following the guidelines @ your README.md.
    private Long expiresAt;

    public ShortenerDTO(String originalUrl, Long expiresAt) {
        this.expiresAt = expiresAt;
        this.originalUrl = originalUrl;
    }

}
