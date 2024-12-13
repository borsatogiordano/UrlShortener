package borsatogiordanodev.urlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import borsatogiordanodev.urlShortener.entity.UrlEntity;


public interface UrlRepository extends JpaRepository <UrlEntity, Long> {
    
   UrlEntity findByShortUrl(String shortUrl);
}
