package com.backend.makemyimage.domain.image.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity(name="image")
@NoArgsConstructor
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String imageUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Builder
    public Image(Long userId, String keyword, String imageUrl) {
        this.userId = userId;
        this.keyword = keyword;
        this.imageUrl = imageUrl;
    }

}
