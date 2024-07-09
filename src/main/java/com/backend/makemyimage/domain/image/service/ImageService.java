package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageResponse;
import com.backend.makemyimage.domain.image.dto.response.KeywordImageResponse;
import com.backend.makemyimage.domain.image.entity.Image;
import com.backend.makemyimage.domain.image.repository.ImageRepository;
import com.backend.makemyimage.domain.user.entity.User;
import com.backend.makemyimage.domain.user.repository.UserRepository;
import com.backend.makemyimage.global.config.FindUserByToken;
import com.backend.makemyimage.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final FindUserByToken findUserByToken;

    public String createImage(ImageCreateRequest imageCreateRequest) {
        User user = findUserByToken.findUser();
        String imageUrl= KakaoKarloService.getImage(imageCreateRequest);
        imageRepository.save(
                Image.builder()
                        .userId(user.getId())
                        .imageUrl(imageUrl)
                        .keyword(imageCreateRequest.getKeyword())
                        .build());
        return imageUrl;
    }

    public List<ImageResponse> getImages() {
        User user = findUserByToken.findUser();
        return imageRepository.findAllByUserId(user.getId()).stream().map(ImageResponse::new).toList();
    }

    public KeywordImageResponse getImageById(Long id) {
        Image image= imageRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Image not found"));
        return new KeywordImageResponse(image);
    }

    @Transactional
    public void deleteImageById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Image not found"));
        image.deleteImage();
    }
}
