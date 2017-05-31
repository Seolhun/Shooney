package com.shun.mongodb.model.log;

import com.shun.mongodb.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@SuppressWarnings("serial")
@Document(collection = "DC_LOG_DATA")
@Getter
@Setter
public class LogData extends BaseEntity implements Serializable {
    @Field(value = "LOG_IDX")
    @Indexed(unique = false)
    private Long idx;

    @Field(value = "LOG_TYPE")
    private String type;

    @Field(value = "LOG_GENERATED_URL")
    private String generatedUrl;

    @Field(value = "LOG_TARTGET")
    private List<String> target;

    @Field(value = "LOG_KEYWORDS")
    private List<String> keywords;

    @Field(value = "LOG_CREATED_DATE")
    private Date createdDate;

    @Field(value = "LOG_CREATED_BY")
    private String createdBy;

    @Field(value = "LOG_MODIFIED_DATE")
    private Date modifiedDate;

    @Field(value = "news_Modified_By")
    private String modifiedBy;

    @Field(value = "LOG_DEL_FLAG")
    private boolean delFlag = false;
}