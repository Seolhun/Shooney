package com.shun.mongodb.model.github.search;

import java.io.Serializable;

public enum SearchOption implements Serializable {
    내림차순("asc"),
    오름차순("desc");

    private String optionName;

    SearchOption(String optionName) {
        this.optionName = optionName;
    }

    public String getType() {
        return optionName;
    }

    public void setType(String optionName) {
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return this.optionName;
    }

    public String getName() {
        return this.name();
    }
}
