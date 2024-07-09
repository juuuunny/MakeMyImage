package com.backend.makemyimage.domain.image.repository;

import com.backend.makemyimage.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
