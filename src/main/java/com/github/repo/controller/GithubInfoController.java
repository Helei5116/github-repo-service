package com.github.repo.controller;

import com.github.repo.dto.GithubRepoResponse;
import com.github.repo.service.GithubInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repositories")
@Slf4j
public class GithubInfoController {

    @Autowired
    private GithubInfoService githubInfoService;

    @GetMapping("/{owner}/{repository-name}")
    public ResponseEntity<GithubRepoResponse> getGithubInfo(
            @PathVariable String owner,
            @PathVariable("repository-name") String repoName) {
        log.info("getGithubInfo param is [owner:{},repoName:{}]", owner, repoName);
        GithubRepoResponse response = githubInfoService.getGithubInfoDetails(owner, repoName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 全局异常处理
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}