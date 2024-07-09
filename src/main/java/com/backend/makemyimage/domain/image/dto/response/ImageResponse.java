package com.backend.makemyimage.domain.image.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageResponse {
    private String imageUrl;
    private LocalDateTime createdAt;
    private String keyword;

    @Builder
    public ImageResponse(String imageUrl, LocalDateTime createdAt, String keyword) {
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.keyword = keyword;
    }
}
