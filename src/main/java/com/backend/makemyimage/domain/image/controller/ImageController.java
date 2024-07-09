package com.backend.makemyimage.domain.image.controller;

import com.backend.makemyimage.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
}
