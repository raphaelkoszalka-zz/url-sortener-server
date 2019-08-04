package com.koszalka.shortener.persistence.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortenerDTO {

    private String originalUrl;
    private LocalDateTime expiresAt;

    public ShortenerDTO(String originalUrl, LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        this.originalUrl = originalUrl;
    }

}
