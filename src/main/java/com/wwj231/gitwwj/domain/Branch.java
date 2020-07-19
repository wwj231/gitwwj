package com.wwj231.gitwwj.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    @JsonProperty("name")
    private String branchName;

    @JsonProperty("protected")
    private boolean isProtected;

    private CommitInfo commit;
}
