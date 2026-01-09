package com.github.repo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Stores cached GitHub info repository details to avoid repeated API calls
 */
@Entity
@Table(name = "github_info_cache")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubInfoCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fullName; // 仓库完整名称（owner/name）

    private String description;

    @Column(nullable = false)
    private String cloneUrl;

    private Integer stars;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 缓存更新时间
    private LocalDateTime cachedAt = LocalDateTime.now();
}