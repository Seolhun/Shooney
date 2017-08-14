package com.shun.blog.model.stack;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_COMPANY")
public class Company extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Long id;

    @Column(name = "COMPANY_NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "COMPANY_CONTENT", length = 300)
    private String content;

    @Column(name = "COMPANY_URL", length = 100)
    private String url;

    @Column(name = "COMPANY_DEPTH", length = 10)
    private int depth = 0;

    @OneToMany(mappedBy = "companyInFile")
    private List<CompanyFile> companyFiles=new ArrayList<>();

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_COMPANY_STACK", joinColumns = {@JoinColumn(name = "COMPANY_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_ID")})
    private Set<Stack> stacks = new HashSet<>();

    @Transient
    private int type;
}
