package com.shun.blog.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@Getter
@Setter
@ToString
public class Example {

    @Column(name = "CREATED_BY", length = 60, nullable = false)
    private String createdBy;

    @Column(name = "UPDATED_BY", length = 60)
    private String modifiedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE", nullable = false)
    private Date updatedDate;

    @Column(name = "DELETED_FLAG", length = 1)
    private boolean deletedFlag = false;

    @Version
    @Column(name = "VERSION")
    private int version;

    @PrePersist
    protected void onCreate() {
        createdDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}