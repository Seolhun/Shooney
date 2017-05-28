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
@Entity(name = "TB_ITEM_TOOL")
public class ItemTool implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_TOOL_ID")
    private Long id;

    @Column(name = "ITEM_TOOL_NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "ITEM_TOOL_CONTENT", length = 300)
    private String content;

    @Column(name = "ITEM_TOOL_URL", length = 100)
    private String url;

    @Column(name = "ITEM_TOOL_DEPTH", length = 10)
    private int depth = 0;

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_ITEM_TOOL_STACK", joinColumns = {@JoinColumn(name = "ITEM_TOOL_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_ID")})
    private Set<Stack> stacks = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ITEM_TOOL_CREATED_DATE")
    private Date createdDate;

    @Column(name = "ITEM_TOOL_CREATED_BY", nullable = false, length = 60)
    private String createdBy;

    @Column(name = "ITEM_TOOL_MODIFIED_BY", length = 60)
    private String modifiedBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ITEM_TOOL_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "ITEM_TOOL_DEL_FLAG", length = 1)
    private String delFlag="N";

    @Transient
    private int type;
}
