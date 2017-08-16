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
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_ITEM_TOOL")
public class ItemTool extends AbstractCommon implements Serializable {
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

    @Transient
    private int type;
}
