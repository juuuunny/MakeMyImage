package com.backend.makemyimage.domain.image.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ImagesResponse {
    private Long id;
    private String imageUrl;
    private LocalDateTime createdAt;
}
