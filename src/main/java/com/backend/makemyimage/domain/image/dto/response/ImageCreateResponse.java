package com.backend.makemyimage.domain.image.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ImageCreateResponse {
    private String id;
    private String model_version;
    private List<Image> images;

    @Getter
    public static class Image {
        private String id;
        private String image;
        private Long seed;
        private Object nsfw_content_detected;
        private Object nsfw_score;
    }
}
