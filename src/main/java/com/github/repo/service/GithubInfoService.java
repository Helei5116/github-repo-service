package com.github.repo.service;

import com.github.repo.dto.GithubRepoResponse;

public interface GithubInfoService {
    GithubRepoResponse getGithubInfoDetails(String owner, String repoName);
}
