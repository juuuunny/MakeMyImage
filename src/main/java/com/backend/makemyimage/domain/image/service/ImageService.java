package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageCreateResponse;
import com.backend.makemyimage.domain.image.repository.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    @Value("${spring.karlo.api.url}")
    private String API_URL;
    @Value("${spring.karlo.api.key}")
    private String API_KEY;

    public String getImage(ImageCreateRequest imageCreateRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK " + API_KEY);

        String prompt = "A photo of " + imageCreateRequest.getKeyword();
        String requestJson = String.format("{\"version\": \"v2.1\", \"prompt\": \"%s\", \"height\": 1024, \"width\": 1024}", prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();

        try {
            ImageCreateResponse imageResponse = objectMapper.readValue(responseBody, ImageCreateResponse.class);
            if (imageResponse.getImages() != null && !imageResponse.getImages().isEmpty()) {
                return imageResponse.getImages().get(0).getImage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
