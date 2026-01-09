package com.github.repo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * to map response from GitHub Repository API
 * doc：https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#get-a-repository
 */
@Data
public class GithubApiResponse {
    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("stargazers_count")
    private Integer stars;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}