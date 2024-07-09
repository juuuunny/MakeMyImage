package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageCreateResponse;
import com.backend.makemyimage.domain.image.entity.Image;
import com.backend.makemyimage.domain.image.repository.ImageRepository;
import com.backend.makemyimage.domain.user.dto.response.UserInfoResponse;
import com.backend.makemyimage.domain.user.entity.User;
import com.backend.makemyimage.domain.user.repository.UserRepository;
import com.backend.makemyimage.domain.user.security.CustomUserDetails;
import com.backend.makemyimage.global.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final KakaoKarloService kakaoKarloService;

    public String createImage(ImageCreateRequest imageCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = userDetails.getEmail(); // 사용자 이메일

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));

        String image= kakaoKarloService.getImage(imageCreateRequest);

        imageRepository.save(
                Image.builder()
                        .userId(user.getId())
                        .imageUrl(image)
                        .keyword(imageCreateRequest.getKeyword())
                        .build());
        return image;
    }
}
