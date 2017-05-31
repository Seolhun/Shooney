package com.shun.mongodb.model.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by HunSeol on 2017. 5. 27..
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubOwner implements Serializable{
    @JsonProperty(value="id")
    private Long ownerId;

    @JsonProperty(value="login")
    private String login;

    @JsonProperty(value="html_url")
    private String html_url;

    @JsonProperty(value="type")
    private String type;

    @JsonProperty(value="site_admin")
    private boolean site_admin;

}
