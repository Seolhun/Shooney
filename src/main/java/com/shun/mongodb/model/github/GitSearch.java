package com.shun.mongodb.model.github;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HunSeol on 2017. 5. 27..
 */
@Getter
@Setter
@ToString
public class GitSearch implements Serializable {
    // /search/repositories
    // /search/commits
    //
    private GithubSearchType githubSearchType;

    private String searchType;

    // &sort=stars
    // &sort=forks
    private String sort;

    // &order=desc, asc
    private String order;

    //true & only
    private String fork;

    //topic:jekyll
    //topic>3
    //topic:5
    @JsonSerialize
    @JsonDeserialize
    private List<String> topcis = new ArrayList<>();

    private String language;

    //forks:>=200
    private int forks;

    //size:1000 : Matches repositories that are 1 MB exactly.
    //size:>=30000 : Matches repositories that are at least 30 MB.
    //size:<50 : Matches repositories that are smaller than 50 KB.
    //size:50..120 : Matches repositories that are between 50 KB and 120 KB.
    private int minSize;
    private int maxSize;
    private int minStars;
    private int maxStars;

    //author:defunkt
    //author-name:wanstrath
    //author-email:chris@github.com
    private String author;


    //?q=repo
    //?q=user
    //?q=is:public ,private
    // q=python , q=topic:chatbot
}
