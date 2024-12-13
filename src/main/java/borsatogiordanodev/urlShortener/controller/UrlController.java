package borsatogiordanodev.urlShortener.controller;

import org.springframework.web.bind.annotation.RestController;

import borsatogiordanodev.urlShortener.controller.dtos.UrlRequestDTO;
import borsatogiordanodev.urlShortener.controller.dtos.UrlResponseDTO;
import borsatogiordanodev.urlShortener.entity.UrlEntity;
import borsatogiordanodev.urlShortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @PostMapping("/shorten")
    ResponseEntity<UrlResponseDTO> shortenUrl(@RequestBody UrlRequestDTO urlRequestDto) {

        UrlEntity urlEntity = urlService.shortenUrl(urlRequestDto.originalUrl());

        UrlResponseDTO responseDTO = new UrlResponseDTO(
                urlEntity.getOriginalUrl(),
                urlEntity.getShortUrl());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{shortUrl}")
    public void redirectToUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {

        UrlEntity urlEntity = urlService.getOriginalUrl(shortUrl);

        if (urlEntity != null) {
            response.sendRedirect(urlEntity.getOriginalUrl());
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

}
