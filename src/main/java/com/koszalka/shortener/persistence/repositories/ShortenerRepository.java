package com.koszalka.shortener.persistence.repositories;

import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends JpaRepository<ShortenerEntity, Long> {

    @Query("SELECT short"
        + " FROM ShortenerEntity as short"
        + " WHERE short.hash = :hash")
    ShortenerEntity getOriginalUrlFromHash(@Param("hash") String hash);

    @Query("SELECT COUNT(short.id)"
        + " FROM ShortenerEntity as short"
        + " WHERE short.hash = :hash")
    Long verifyIfHashAlreadyExist(@Param("hash") String hash);

}
