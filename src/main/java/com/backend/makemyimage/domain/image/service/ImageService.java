package com.backend.makemyimage.domain.image.service;

import com.backend.makemyimage.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

}
