package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageResponse;
import com.backend.makemyimage.domain.image.dto.response.ImagesResponse;
import com.backend.makemyimage.domain.image.entity.Image;
import com.backend.makemyimage.domain.image.repository.ImageRepository;
import com.backend.makemyimage.domain.user.entity.User;
import com.backend.makemyimage.domain.user.repository.UserRepository;
import com.backend.makemyimage.domain.user.security.CustomUserDetails;
import com.backend.makemyimage.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public String createImage(ImageCreateRequest imageCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = userDetails.getEmail(); // 사용자 이메일

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));

        String image= KakaoKarloService.getImage(imageCreateRequest);

        imageRepository.save(
                Image.builder()
                        .userId(user.getId())
                        .imageUrl(image)
                        .keyword(imageCreateRequest.getKeyword())
                        .build());
        return image;
    }


    public List<ImagesResponse> getImages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String email = userDetails.getEmail(); // 사용자 이메일

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));

        List<Image> images=imageRepository.findAllByUserId(user.getId());
        return images.stream().map(image->new ImagesResponse(image.getId(),image.getImageUrl(),image.getCreatedAt())).collect(Collectors.toList());

    }

    public ImageResponse getImageById(Long id) {
        Image image= imageRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Image not found"));

        ImageResponse imageResponse = ImageResponse
                .builder()
                .imageUrl(image.getImageUrl())
                .createdAt(image.getCreatedAt())
                .keyword(image.getKeyword())
                .build();
        return imageResponse;
    }

    @Transactional
    public void deleteImageById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Image not found"));
        image.deleteImage();
    }
}
