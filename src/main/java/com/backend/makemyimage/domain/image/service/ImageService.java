package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageCreateResponse;
import com.backend.makemyimage.domain.image.repository.ImageRepository;
import com.backend.makemyimage.global.exception.CustomException;
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
    private final KakaoKarloService kakaoKarloService;

    public String createImage(ImageCreateRequest imageCreateRequest) {


        return KakaoKarloService.getImage(imageCreateRequest);
    }
}
