package com.backend.makemyimage.domain.image.controller;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.service.ImageService;
import com.backend.makemyimage.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/image")
    public ResponseEntity<ApiResponse<String>> createImage(@RequestBody ImageCreateRequest imageCreateRequest) {
        String image = imageService.createImage(imageCreateRequest);
        ApiResponse<String> response=new ApiResponse<>(HttpStatus.OK,"Create Image Successfully",image);
        return ResponseEntity.ok(response);
    }


}
