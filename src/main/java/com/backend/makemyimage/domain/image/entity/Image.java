package com.backend.makemyimage.domain.image.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String keyword;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean deleted = false;

}
