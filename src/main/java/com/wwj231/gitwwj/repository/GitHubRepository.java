package com.wwj231.gitwwj.repository;

import com.wwj231.gitwwj.domain.Branch;
import com.wwj231.gitwwj.domain.RepoGitHub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Repository
@Slf4j
public class GitHubRepository {
    private static final String URL = "https://api.github.com/users/{user}/repos";
    private static final String GET_BRANCHES_URL = "https://api.github.com/repos/{owner}/{repo}/branches";
    private static final String URL_USER_PARAM = "user";
    private static final String URL_OWNER_PARAM = "owner";
    private static final String URL_REPO_PARAM = "repo";
    
    private final RestTemplate restTemplate;
    private final String desiredRepository;
    private final String desiredUser;

    public GitHubRepository(final RestTemplate restTemplate,
                            @Value("${git-hub.desired.repo}") final String desiredRepository,
                            @Value("${git-hub.desired.user}") final String desiredUser) {
        this.restTemplate = restTemplate;
        this.desiredRepository = desiredRepository;
        log.info("git-hub.desired.repo : [{}]", desiredRepository);
        this.desiredUser = desiredUser;
        log.info("git-hub.desired.user : [{}]", desiredUser);
    }

    public String readGitHubReposForUser(String username) {
        Map<String, String> param = Map.of(
                URL_USER_PARAM, username
        );

        var response = restTemplate.getForEntity(URL, String.class, param);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }

    public RepoGitHub[] listGitHubReposForUser(String username) {
        Map<String, String> param = Map.of(
                URL_USER_PARAM, username
        );

        RepoGitHub[] result = new RepoGitHub[0];
        ResponseEntity<RepoGitHub[]> response = restTemplate.getForEntity(URL, RepoGitHub[].class, param);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return result;
    }

    public Branch[] listBranches(){
        Map<String, String> params = Map.of(
                URL_OWNER_PARAM, desiredUser,
                URL_REPO_PARAM, desiredRepository
        );

        Branch[] result = new Branch[0];
        ResponseEntity<Branch[]> response = restTemplate.getForEntity(GET_BRANCHES_URL, Branch[].class, params);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return result;
    }
}
