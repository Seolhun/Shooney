package com.shun.blog.model.stack;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "TB_STACK", uniqueConstraints = {@UniqueConstraint(columnNames = "STACK_NAME")})
@BatchSize(size = 5)
public class Stack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STACK_ID")
    private Long id;

    @Column(name = "STACK_NAME", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "STACK_CONTENT", length = 300)
    private String content;

    @Column(name = "STACK_URL", length = 100)
    private String url;

    @Column(name = "STACK_LANG_DEPTH", length = 10)
    private int langDepth = 0;

    @Column(name = "STACK_COMPANY_DEPTH", length = 10)
    private int companyDepth = 0;

    @Column(name = "STACK_TOOL_DEPTH", length = 10)
    private int toolDepth = 0;

    @OneToMany(mappedBy = "stackInFile")
    private List<StackFile> stackImgFiles = new ArrayList<>();

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STACK_SIMILAR", joinColumns = {@JoinColumn(name = "STACK_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_SIMILAR_ID")})
    private List<Stack> similarStacks = new ArrayList<>();

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STACK_COMPANY", joinColumns = {@JoinColumn(name = "STACK_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_COMPANY_ID")})
    private List<Company> companies = new ArrayList<>();;

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STACK_ITEM", joinColumns = {@JoinColumn(name = "STACK_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_ITEM_ID")})
    private List<ItemTool> items = new ArrayList<>();;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STACK_CREATED_DATE")
    private Date createdDate;

    @Column(name = "STACK_CREATED_BY",  length = 60)
    private String createdBy;

    @Column(name = "STACK_MODIFIED_BY", length = 60)
    private String modifiedBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STACK_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "STACK_DEL_FLAG", length = 1)
    private String delFlag="N";

    @Transient
    private int type;
}
