package com.backend.makemyimage.domain.image.controller;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.ImageResponse;
import com.backend.makemyimage.domain.image.dto.response.ImagesResponse;
import com.backend.makemyimage.domain.image.service.ImageService;
import com.backend.makemyimage.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/images")
    public ResponseEntity<ApiResponse<List<ImagesResponse>>> getImages(){
        List<ImagesResponse> images=imageService.getImages();
        ApiResponse<List<ImagesResponse>> response=new ApiResponse<>(HttpStatus.OK,"Show Images Successfully",images);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ApiResponse<ImageResponse>> getImageById(@PathVariable Long id) {
        ImageResponse image = imageService.getImageById(id);
        ApiResponse<ImageResponse> response=new ApiResponse<>(HttpStatus.OK,"Show Image Successfully",image);
        return ResponseEntity.ok(response);
    }



}
