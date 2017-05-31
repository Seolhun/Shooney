package com.shun.mongodb.model.github.search;

import java.io.Serializable;

public enum GithubSearchType implements Serializable {
    Repositories("/repositories"),
    Code("/code"),
    Issues("/issues"),
    Commits("/commits"),
    Users("/users"),
    Legacy("/legacy");

    private String searchType;

    GithubSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getType() {
        return searchType;
    }

    public void setType(String searchType) {
        this.searchType = searchType;
    }

    @Override
    public String toString() {
        return this.searchType;
    }

    public String getName() {
        return this.name();
    }
}
