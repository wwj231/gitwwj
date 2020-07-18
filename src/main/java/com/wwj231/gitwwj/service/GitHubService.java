package com.wwj231.gitwwj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwj231.gitwwj.domain.Branch;
import com.wwj231.gitwwj.domain.RepoGitHub;
import com.wwj231.gitwwj.repository.GitHubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GitHubService {

    private final GitHubRepository gitHubRepository;

    public GitHubService(final GitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    public List<RepoGitHub> getGitHubReposForUser(final String gitHubUser) {
        log.info("user [{}]", gitHubUser);
        var result = gitHubRepository.readGitHubReposForUser(gitHubUser);
        log.info("user: [{}] repositories: [{}]", gitHubUser, result);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper
                    .readValue(result,
                            objectMapper
                                    .getTypeFactory()
                                    .constructCollectionType(ArrayList.class, RepoGitHub.class));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException in GitHubRepository.readGitHubReposForUser");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<RepoGitHub> getGitHubReposForUser1(final String gitHubUser) {
        List<RepoGitHub> result = Collections.emptyList();
        result = Arrays.stream(gitHubRepository.listGitHubReposForUser(gitHubUser)).collect(Collectors.toList());
        log.info("user: [{}] repositories: [{}]", gitHubUser, result);
        return result;
    }

    public List<Branch> getBranches(){
        List<Branch> result = Collections.emptyList();
        result = Arrays.stream(gitHubRepository.listBranches()).collect(Collectors.toList());
        log.info("getBranches() received: {}", result);
        return result;
    }
}
