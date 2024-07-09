package com.backend.makemyimage.domain.image.dto.response;

import com.backend.makemyimage.domain.image.entity.Image;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class KeywordImageResponse {
    private String imageUrl;
    private LocalDateTime createdAt;
    private String keyword;

    public KeywordImageResponse(Image image){
        this.imageUrl = image.getImageUrl();
        this.createdAt = image.getCreatedAt();
        this.keyword = image.getKeyword();
    }
}
