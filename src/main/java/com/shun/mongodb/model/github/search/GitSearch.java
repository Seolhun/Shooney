package com.shun.mongodb.model.github.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
    private String searchType;

    //?q=tetris+chatbot
    private List<String> names;

    //+topic:jekyll
    //+topic>3
    //+topic:5
    private List<String> topics;

    // q=tetris+language:python+language:java
    private List<String> languages;

    //+size:1000 : Matches repositories that are 1 MB exactly.
    //+size:>=30000 : Matches repositories that are at least 30 MB.
    //+size:<50 : Matches repositories that are smaller than 50 KB.
    //+size:50..120 : Matches repositories that are between 50 KB and 120 KB.
    //+forks:>=200
    //+forks:10..30&
    private Integer minSize;
    private Integer maxSize;
    private Integer minStars;
    private Integer maxStars;
    private Integer minForks;
    private Integer maxForks;

    // &sort=stars
    // &sort=forks
    private String sort;

    // &order=desc, asc
    private String order;

    //author:defunkt
    //author-name:wanstrath
    //author-email:chris@github.com
    private String author;

    //true & only
    private String fork;

    //?q=repo
    //?q=user
    // q=python , q=topic:chatbot
    //?q=is:public ,private
}
