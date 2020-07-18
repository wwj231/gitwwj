package com.wwj231.gitwwj.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwj231.gitwwj.domain.RepoGitHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class GitHubRepository {
    private static final String URL = "https://api.github.com/users/{user}/repos";
    private static final String URL_USER_PARAM = "user";
    private final RestTemplate restTemplate;

    public GitHubRepository(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<List<RepoGitHub>> readGitHubReposForUser(String username) {
        Map<String, String> param = Map.of(
                URL_USER_PARAM, username
        );

        var response = restTemplate.getForEntity(URL, String.class, param);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Optional.ofNullable(objectMapper
                    .readValue(response.getBody(),
                            objectMapper
                                    .getTypeFactory()
                                    .constructCollectionType(ArrayList.class, RepoGitHub.class)));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException in GitHubRepository readGitHubReposForUser");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public RepoGitHub[] readGitHubReposForUser1(String username) {
        Map<String, String> param = Map.of(
                URL_USER_PARAM, username
        );

        ResponseEntity<RepoGitHub[]> response = restTemplate.getForEntity(URL, RepoGitHub[].class, param);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }
}
