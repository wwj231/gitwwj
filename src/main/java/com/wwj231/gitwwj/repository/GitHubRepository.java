package com.wwj231.gitwwj.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwj231.gitwwj.domain.RepoGitHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class GitHubRepository {
    private static final String URL_GIT_HUB_CONNECTION_USER_REPO = "https://api.github.com/users/%s/repos";
    private final RestTemplate restTemplate;

    public GitHubRepository(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<List<RepoGitHub>> readGitHubReposForUser(String username){
        String URL_FOR_USER = String.format(URL_GIT_HUB_CONNECTION_USER_REPO, username);
        log.info("URL FOR USER : [{}]", URL_FOR_USER);

        var response = restTemplate.getForEntity(URL_FOR_USER, String.class);

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
}
