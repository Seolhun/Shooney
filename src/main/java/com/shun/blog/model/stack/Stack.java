package com.shun.blog.model.stack;

import com.shun.blog.model.user.UserProfile;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "TB_STACK")
public class Stack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STACK_ID")
    private Long id;

    @Column(name = "STACK_NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "STACK_CONTENT", length = 300, nullable = false)
    private String content;

    @Column(name = "STACK_URL", length = 100)
    private String url;

    @Column(name = "STACK_DEPTH", length = 10)
    private int depth = 0;

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STACK_SIMILAR", joinColumns = {@JoinColumn(name = "STACK_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_ID_SIMILAR")})
    private Set<Stack> stacks = new HashSet<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STACK_CREATED_DATE")
    private Date createdDate;

    @Column(name = "STACK_CREATED_BY", nullable = false, length = 60)
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
