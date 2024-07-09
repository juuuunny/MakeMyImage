package com.backend.makemyimage.domain.image.dto.response;

import com.backend.makemyimage.domain.image.entity.Image;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageResponse {
    private Long id;
    private String imageUrl;
    private LocalDateTime createdAt;

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.imageUrl = image.getImageUrl();
        this.createdAt = image.getCreatedAt();
    }
}
