package com.wwj231.gitwwj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoGitHub {

    private Long id;
    private String name;
    private String full_name;
    private RepoGitHubOwner owner;
    private Date created_at;
    private Date updated_at;
    private Date pushed_at;


}
