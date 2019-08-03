package com.koszalka.shortener.persistence.repositories;

import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends JpaRepository<ShortenerEntity, Long> {

}
