package com.wwj231.gitwwj.service;

import com.wwj231.gitwwj.domain.RepoGitHub;
import com.wwj231.gitwwj.repository.GitHubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GitHubService {

    private final GitHubRepository gitHubRepository;

    public GitHubService(final GitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    public Optional<List<RepoGitHub>> getGitHubReposForUser(final String gitHubUser) {
        log.info("user [{}]", gitHubUser);
        Optional result = gitHubRepository.readGitHubReposForUser(gitHubUser);
        log.info("user: [{}] repositories: [{}]", gitHubUser, result);
        return result;
    }
}
