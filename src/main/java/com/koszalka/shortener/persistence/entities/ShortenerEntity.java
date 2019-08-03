package com.koszalka.shortener.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "url", schema = "public")
@Getter
@Setter
public class ShortenerEntity {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "original", nullable = false)
    private String original;

    @Column(name = "shortened", nullable = false)
    private String shortened;

}
