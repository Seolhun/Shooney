package com.shun.mongodb.model.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by HunSeol on 2017. 5. 27..
 */
@Getter
@Setter
public class GithubRepository implements Serializable {
    @JsonProperty(value = "idx")
    private Long idx;

    @JsonProperty(value = "owner")
    private GithubOwner owner;

    @JsonProperty(value = "html_url")
    private String html_url;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "clone_url")
    private String clone_url;

    @JsonProperty(value = "svn_url")
    private String svn_url;

    @JsonProperty(value = "forks")
    private Integer forks;

    @JsonProperty(value = "size")
    private int size;

    @JsonProperty(value = "open_issues")
    private Integer open_issues;

    @JsonProperty(value = "watchers")
    private Integer watchers;

    @JsonProperty(value = "score")
    private float score;

    @JsonProperty(value = "defaultBranch")
    private String defaultBranch;

    @JsonProperty(value = "created_at")
    private Date created_at;

    @JsonProperty(value = "updated_at")
    private Date updated_at;

    @JsonProperty(value = "pushed_at")
    private Date pushed_at;
}
