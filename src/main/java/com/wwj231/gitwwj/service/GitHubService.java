package com.wwj231.gitwwj.service;

import com.wwj231.gitwwj.domain.RepoGitHub;
import com.wwj231.gitwwj.repository.GitHubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Optional<List<RepoGitHub>> getGitHubReposForUser1(final String gitHubUser) {
        log.info("user [{}]", gitHubUser);
        var result = gitHubRepository.readGitHubReposForUser1(gitHubUser);
        log.info("user: [{}] repositories: [{}]", gitHubUser, result);
        List<RepoGitHub> repoGitHubList = new ArrayList<>(Arrays.asList(result));
        Optional optional = Optional.ofNullable(repoGitHubList);
        return optional;
    }
}
