package com.wwj231.gitwwj.controllers;

import com.wwj231.gitwwj.domain.RepoGitHub;
import com.wwj231.gitwwj.service.GitHubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class GitHubController {
    private final GitHubService gitHubService;
    private final String gitHubUser;

    public GitHubController(final GitHubService gitHubService, @Value("${git-hub.user}") String gitHubUser) {
        this.gitHubService = gitHubService;
        log.info("set git hub user: [{}]", gitHubUser);
        this.gitHubUser = gitHubUser;
    }

    @GetMapping("/git-repos")
    public ResponseEntity<List<RepoGitHub>> getGitHubRepositoriesForUser(){
        log.info("git hub user: [{}]", gitHubUser);
        var result = gitHubService.getGitHubReposForUser(gitHubUser);

        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
