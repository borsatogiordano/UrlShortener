package borsatogiordanodev.urlShortener.service;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import borsatogiordanodev.urlShortener.entity.UrlEntity;
import borsatogiordanodev.urlShortener.repository.UrlRepository;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public UrlEntity shortenUrl(String urlOriginal) {

        UrlEntity urlEntity = new UrlEntity();

        urlEntity.setOriginalUrl(urlOriginal);
        urlEntity.setShortUrl(generateShortUrl());

        return urlRepository.save(urlEntity);
    }

    public UrlEntity getOriginalUrl(String shortUrl) {
        try {
            return urlRepository.findByShortUrl(shortUrl);
        } catch (Exception e) {
            throw new RuntimeException("URL not found", e);
        }
    }

    public String generateShortUrl() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }
}
