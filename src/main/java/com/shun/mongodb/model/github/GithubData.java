package com.shun.mongodb.model.github;

import com.shun.mongodb.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * Created by HunSeol on 2017. 5. 27..
 */
@SuppressWarnings("serial")
@Document(collection = "GithubData")
@Getter
@Setter
public class GithubData extends BaseEntity{
    @Field(value="GITHUB_IDX")
    @Indexed
    private Long idx;

    @Field(value="GITHUB_PARAM")
    private String param;

    @Field(value="GITHUB_API_TYPE")
    private String apiType;

    @Field(value="GITHUB_CONTENT")
    private String content;

    @Field(value="GITHUB_CREATED_BY")
    private String createdBy;
    
    @Field(value="GITHUB_CREATED_DATE")
    private Date createdDate;

    @Field(value="github_Modified_By")
    private String modifiedBy;

    @Field(value="GITHUB_MODIFIED_DATE")
    private Date modifiedDate;

    @Field(value="GITHUB_DEL_CHECK")
    private String delFalg="N";
}
