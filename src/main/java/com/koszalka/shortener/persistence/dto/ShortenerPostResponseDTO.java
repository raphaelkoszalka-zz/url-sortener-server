package com.koszalka.shortener.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ShortenerPostResponseDTO {

    private boolean success;

    public ShortenerPostResponseDTO(boolean success) {
        this.success = success;
    }

}
