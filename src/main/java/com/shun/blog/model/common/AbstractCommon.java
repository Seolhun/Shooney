package com.shun.blog.model.common;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractCommon {

    @Column(name = "CREATED_BY", length = 60)
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
    private boolean deletedFlag;

    @Version
    @Column(name = "VERSION")
    private int version;

    //Repository or service divide
    @Transient
    private int queryType;

    @PrePersist
    protected void onCreate() {
        createdDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}