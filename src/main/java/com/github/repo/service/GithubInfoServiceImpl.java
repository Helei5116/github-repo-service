package com.github.repo.service;

import com.github.repo.dto.GithubApiResponse;
import com.github.repo.dto.GithubRepoResponse;
import com.github.repo.entity.GithubInfoCache;
import com.github.repo.repository.GithubInfoCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class GithubInfoServiceImpl implements GithubInfoService {
    @Autowired
    private WebClient githubWebClient;
    @Autowired
    private GithubInfoCacheRepository githubInfoCacheRepository;

    @Override
    public GithubRepoResponse getGithubInfoDetails(String owner, String repoName) {
        String fullName = String.format("%s/%s", owner, repoName);

        // 1. Query from the cache first
        GithubInfoCache cachedRepo = githubInfoCacheRepository.findByFullName(fullName)
                .orElse(null);

        if (cachedRepo != null) {
            // if this not null,then convert to response
            return mapToResponse(cachedRepo);
        }

        // 2. if cache result is null，then get result from GitHub API
        GithubApiResponse githubResponse;
        try {
            githubResponse = githubWebClient.get()
                    .uri("/{owner}/{repoName}", owner, repoName)
                    .retrieve()
                    .bodyToMono(GithubApiResponse.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("Repository not found: " + fullName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch data from GitHub API: " + e.getMessage());
        }

        // 3. Cache the result
        GithubInfoCache newCache = new GithubInfoCache();
        newCache.setFullName(githubResponse.getFullName());
        newCache.setDescription(githubResponse.getDescription());
        newCache.setCloneUrl(githubResponse.getCloneUrl());
        newCache.setStars(githubResponse.getStars());
        newCache.setCreatedAt(githubResponse.getCreatedAt());
        githubInfoCacheRepository.save(newCache);

        // 4. Convert to response
        return mapToResponse(newCache);
    }

    /**
     * Convert the cached entity to response
     */
    private GithubRepoResponse mapToResponse(GithubInfoCache cache) {
        GithubRepoResponse response = new GithubRepoResponse();
        response.setFullName(cache.getFullName());
        response.setDescription(cache.getDescription());
        response.setCloneUrl(cache.getCloneUrl());
        response.setStars(cache.getStars());
        response.setCreatedAt(cache.getCreatedAt());
        return response;
    }
}
