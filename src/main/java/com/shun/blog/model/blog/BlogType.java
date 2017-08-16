package com.shun.blog.model.blog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "TB_BLOG_TYPE", uniqueConstraints = {@UniqueConstraint(columnNames = "BLOG_TYPE_NAME")})
public class BlogType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_TYPE_ID")
    private Long id;

    @Column(name = "BLOG_TYPE_NAME", length = 150, unique = true)
    private String name;

    @Column(name = "BLOG_TYPE_COUNTS")
    private int counts = 0;

    @Column(name = "BLOG_TYPE_DEPTH")
    private int depth = 0;

    @Column(name = "BLOG_TYPE_CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "BLOG_TYPE_MODIFIED_BY", length = 60)
    private String modifiedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BLOG_TYPE_CREATED_DATE")
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BLOG_TYPE_MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "BLOG_TYPE_DEL_FLAG", length = 1)
    private String delFlag = "N";

    @Version
    @Column(name = "VERSION")
    private int version;

    public BlogType() {

    }

    public BlogType(Long Id) {
        this.id = id;
    }

    public BlogType(String name) {
        this.name = name;
    }

    public BlogType(Long Id, String name) {
        this.id = id;
        this.name = name;
    }
}
