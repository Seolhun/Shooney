package com.shun.blog.model.stack;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_STACK")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "STACK_NAME")})
public class Stack extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STACK_ID")
    private Long id;

    @Column(name = "STACK_NAME", length = 30, nullable = false, unique = true)
    private String name;

    @Column(name = "STACK_CONTENT", length = 300)
    private String content;

    @Column(name = "STACK_URL", length = 200)
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
    @JoinTable(name = "TB_STACK_SIMILAR", joinColumns = {@JoinColumn(name = "STACK_ID")},
            inverseJoinColumns = {@JoinColumn(name="STACK_SIMILAR_ID")})
    private List<Stack> similarStacks = new ArrayList<>();

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STACK_COMPANY", joinColumns = {@JoinColumn(name = "STACK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STACK_COMPANY_ID")})
    private List<Company> companies = new ArrayList<>();;

    @BatchSize(size = 5)
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STACK_ITEM", joinColumns = {@JoinColumn(name = "STACK_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "STACK_ITEM_ID")})
    private List<ItemTool> items = new ArrayList<>();;


    @Column(name = "STACK_ERROR_FLAG", length = 1)
    private String errorFlag="N";

    @Transient
    private int type;
}
