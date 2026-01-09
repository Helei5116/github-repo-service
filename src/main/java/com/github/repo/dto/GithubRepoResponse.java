package com.github.repo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Final response returned to the client
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepoResponse {
    private String fullName;
    private String description;
    private String cloneUrl;
    private Integer stars;
    private LocalDateTime createdAt;
}