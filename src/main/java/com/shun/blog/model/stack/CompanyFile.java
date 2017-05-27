package com.shun.blog.model.stack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shun.blog.model.blog.Blog;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TB_COMPANY_FILE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CompanyFile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID")
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "COMPANY_FILE_FK"), name = "COMPANY_FILE_ID", referencedColumnName = "COMPANY_ID")
	private Company companyInFile;

	@Column(name = "COMPANY_ORIGIN_NAME", nullable = false, length = 100)
	private String originName;

	@Column(name = "COMPANY_SAVED_NAME", nullable = false, length = 200)
	private String savedName;

	@Column(name = "COMPANY_SAVED_PATH", nullable = false, length = 200)
	private String savedPath;

	@Column(name = " COMPANY_TYPE", nullable = false, length = 20)
	private String type;
	
	@Column(name = " COMPANY_SIZE", nullable = false, length = 20)
	private Long size;
	
	@Column(name = "COMPANY_CREATED_BY", nullable = false, length = 60)
	private String createdBy;

	@Column(name = "COMPANY_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPANY_CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPANY_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "COMPANY_DEL_FLAG", length=1)
	private String delFlag="N";
	
	@Transient
	private byte[] fileByte;
	
	@Transient
	private MultipartFile[] files;
}
