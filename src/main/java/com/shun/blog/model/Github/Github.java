package com.shun.blog.model.Github;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shun.blog.model.common.Paging;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hunseol on 2017. 5. 26..
 */

@Data
@Entity
@Table(name = "TB_GITHUB")
public class Github implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GITHUB_ID")
    private Long id;

    @Column(name = "GITHUB_CONTENT", length=300, nullable = false)
    private String content;

    @Column(name = "GITHUB_LIKES")
    private int likes=0;

    @Column(name = "GITHUB_HATES")
    private int hates=0;

    @Column(name = "GITHUB_CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "GITHUB_MODIFIED_BY", length = 60)
    private String modifiedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "GITHUB_CREATED_DATE")
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "GITHUB_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "GITHUB_DEL_FLAG", length = 1)
    private String delFlag="N";

    @Transient
    private int currentPage;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Paging paging;
}
