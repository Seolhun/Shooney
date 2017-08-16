package com.shun.blog.model.stack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shun.blog.model.common.AbstractCommon;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_COMPANY_FILE")
@BatchSize(size = 5)
public class CompanyFile extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "COMPANY_FILE_FK"), name = "COMPANY_FILE_ID", referencedColumnName = "COMPANY_ID")
    private Company companyInFile;

    @Column(name = "COMPANY_ORIGIN_NAME", length = 100)
    private String originName;

    @Column(name = "COMPANY_SAVED_NAME", length = 200)
    private String savedName;

    @Column(name = "COMPANY_SAVED_PATH", length = 200)
    private String savedPath;

    @Column(name = " COMPANY_TYPE", length = 20)
    private String type;

    @Column(name = " COMPANY_SIZE", length = 20)
    private Long size;

    @Transient
    private byte[] fileByte;

    @Transient
    private MultipartFile[] files;
}
