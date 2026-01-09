package com.github.repo.controller;

import com.github.repo.dto.GithubRepoResponse;
import com.github.repo.entity.GithubInfoCache;
import com.github.repo.repository.GithubInfoCacheRepository;
import com.github.repo.service.GithubInfoService;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GithubInfoControllerE2ETest {

    @Autowired
    private GithubInfoCacheRepository githubInfoCacheRepository;

    @Autowired
    private GithubInfoService githubInfoService;

    private final String TEST_FULL_NAME = "spring-projects/spring-boot";

    @Test
    public void testGetGithubInfoDetails(){
        GithubRepoResponse githubInfoDetails = githubInfoService.getGithubInfoDetails("spring-projects","spring-boot");
        System.out.println("githubInfoDetails = " + JSONValue.toJSONString(githubInfoDetails));
        GithubInfoCache byFullName = githubInfoCacheRepository.findByFullName(TEST_FULL_NAME).orElse(null);
        System.out.println("byFullName = " + JSONValue.toJSONString(byFullName));
    }
}