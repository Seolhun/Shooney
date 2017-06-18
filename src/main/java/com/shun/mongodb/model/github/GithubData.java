package com.shun.mongodb.model.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shun.blog.model.common.Paging;
import com.shun.mongodb.model.BaseEntity;
import com.shun.mongodb.model.github.search.GitSearch;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Github Model
 * @Created by  HunSeol
 * @on 2017. 5. 27..
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@Document(collection = "GITHUB_DATA")
public class GithubData extends BaseEntity implements Serializable{
    @Field(value="GITHUB_IDX")
    @Indexed(direction = IndexDirection.ASCENDING)
    private Long idx;

    @JsonProperty(value = "total_count")
    @Field(value="GITHUB_TOTAL_COUNT")
    private Integer totalCount;

    @JsonProperty(value = "incomplete_results")
    @Field(value="GITHUB_INCOMPLETE_RESULTS")
    private boolean incompleteResults;

    @JsonProperty(value = "items")
    @Field(value="GITHUB_ITEMS")
    private List<GithubRepositories> githubRepositoryList;

    @JsonProperty(value = "search_params")
    @Field(value="GITHUB_SEARCH_PARAM")
    private GitSearch gitSearch;

    @Field(value="GITHUB_DEL_FLAG")
    private boolean delFlag=false;

    @Field(value="GITHUB_CREATED_DATE")
    private Date createdDate=new Date();

    @Field(value="GITHUB_CREATED_BY")
    private String createdBy;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Paging paging;
}
