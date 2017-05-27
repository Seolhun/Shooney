package com.shun.blog.model.stack;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "TB_COMPANY")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Long id;

    @Column(name = "COMPANY_NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "COMPANY_CONTENT", length = 300, nullable = false)
    private String content;

    @Column(name = "COMPANY_URL", length = 100)
    private String url;

    @Column(name = "COMPANY_DEPTH", length = 10)
    private int depth = 0;

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_COMPANY_STACK", joinColumns = {@JoinColumn(name = "COMPANY_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_ID")})
    private Set<Stack> stacks = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPANY_CREATED_DATE")
    private Date createdDate;

    @Column(name = "COMPANY_CREATED_BY", nullable = false, length = 60)
    private String createdBy;

    @Column(name = "COMPANY_MODIFIED_BY", length = 60)
    private String modifiedBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPANY_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "COMPANY_DEL_FLAG", length = 1)
    private String delFlag="N";

    @Transient
    private int type;
}
