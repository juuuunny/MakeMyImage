package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageCreateResponse;
import com.backend.makemyimage.global.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class KakaoKarloService {

    private static String apiUrl;
    private static String apiKey;
    private static RestTemplate restTemplate;
    private static ObjectMapper objectMapper;

    public KakaoKarloService(
            @Value("${spring.karlo.api.url}") String apiUrl,
            @Value("${spring.karlo.api.key}") String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public static String getImage(ImageCreateRequest imageCreateRequest) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK " + apiKey);

        String prompt = "A photo of " + imageCreateRequest.getKeyword();
        String requestJson = String.format("{\"version\": \"v2.1\", \"prompt\": \"%s\", \"height\": 1024, \"width\": 1024}", prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();

        try {
            ImageCreateResponse imageResponse = objectMapper.readValue(responseBody, ImageCreateResponse.class);
            if (imageResponse.getImages() != null && !imageResponse.getImages().isEmpty()) {
                return imageResponse.getImages().get(0).getImage();
            }
        } catch (IOException e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return null;
    }


}
