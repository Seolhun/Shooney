package com.shun.mongodb.model.github.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shun.mongodb.model.github.SearchUser;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HunSeol on 2017. 5. 27..
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitSearch implements Serializable {
    // /search/repositories
    // /search/commitssearchType
    @JsonProperty(value = "searchType")
    private String searchType;

    @JsonProperty(value = "searchUser")
    private SearchUser searchUser;

    //?q=tetris+chatbot
    @JsonProperty(value = "names")
    private List<String> names;

    //+topic:jekyll
    //+topic>3
    //+topic:5
    @JsonProperty(value = "topics")
    private List<String> topics;

    // q=tetris+language:python+language:java
    @JsonProperty(value = "languages")
    private List<String> languages;

    //+size:1000 : Matches repositories that are 1 MB exactly.
    //+size:>=30000 : Matches repositories that are at least 30 MB.
    //+size:<50 : Matches repositories that are smaller than 50 KB.
    //+size:50..120 : Matches repositories that are between 50 KB and 120 KB.
    //+forks:>=200
    //+forks:10..30&
    @JsonProperty(value = "minSize")
    private Integer minSize;
    @JsonProperty(value = "maxSize")
    private Integer maxSize;
    @JsonProperty(value = "minStars")
    private Integer minStars;
    @JsonProperty(value = "maxStars")
    private Integer maxStars;
    @JsonProperty(value = "minForks")
    private Integer minForks;
    @JsonProperty(value = "maxForks")
    private Integer maxForks;

    // &sort=stars
    // &sort=forks
    @JsonProperty(value = "sort")
    private String sort;

    // &order=desc, asc
    @JsonProperty(value = "order")
    private String order;

    //author:defunkt
    //author-name:wanstrath
    //author-email:chris@github.com
    @JsonProperty(value = "author")
    private String author;

    //true & only
    @JsonProperty(value = "fork")
    private String fork;

    @Transient
    @JsonProperty(value = "currentPage")
    private Integer currentPage;

    //?q=repo
    //?q=user
    // q=python , q=topic:chatbot
    //?q=is:public ,private
}
