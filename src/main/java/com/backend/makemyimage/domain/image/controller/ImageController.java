package com.backend.makemyimage.domain.image.controller;

import com.backend.makemyimage.domain.image.dto.request.ImageCreateRequest;
import com.backend.makemyimage.domain.image.dto.response.KeywordImageResponse;
import com.backend.makemyimage.domain.image.dto.response.ImageResponse;
import com.backend.makemyimage.domain.image.service.ImageService;
import com.backend.makemyimage.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/images")
    public ResponseEntity<ApiResponse<String>> createImage(@RequestBody ImageCreateRequest imageCreateRequest) {
        String image = imageService.createImage(imageCreateRequest);
        ApiResponse<String> response=new ApiResponse<>(HttpStatus.CREATED,"Create Image Successfully",image);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/images")
    public ResponseEntity<ApiResponse<List<ImageResponse>>> getImages(){
        List<ImageResponse> images=imageService.getImages();
        ApiResponse<List<ImageResponse>> response=new ApiResponse<>(HttpStatus.OK,"Show Images Successfully",images);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ApiResponse<KeywordImageResponse>> getImageById(@PathVariable Long id) {
        KeywordImageResponse image = imageService.getImageById(id);
        ApiResponse<KeywordImageResponse> response=new ApiResponse<>(HttpStatus.OK,"Show Image Successfully",image);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/images/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteImageById(@PathVariable Long id) {
        imageService.deleteImageById(id);
        ApiResponse<Void> response=new ApiResponse<>(HttpStatus.OK,"Delete Image Successfully",null);
        return ResponseEntity.ok(response);
    }


}
