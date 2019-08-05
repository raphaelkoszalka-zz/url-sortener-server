package com.koszalka.shortener.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @SequenceGenerator(name = "pk_sequence", sequenceName = "pk_sequence", allocationSize = 1, initialValue = 1, schema = "public")
    private Long id;

    @Column(name = "original", nullable = false)
    private String original;

    @Column(name = "shortened", nullable = false)
    private String newUrl;

    @Column(name = "expirationDate", nullable = false)
    private Long expirationDate;

}
