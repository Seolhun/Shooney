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
@Table(name = "TB_STACK_FILE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class StackFile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "STACK_FILE_FK"), name = "STACK_FILE_ID", referencedColumnName = "STACK_ID")
	private Stack stackInFile;

	@Column(name = "STACK_FILE_ORIGIN_NAME", nullable = false, length = 100)
	private String originName;

	@Column(name = "STACK_FILE_SAVED_NAME", nullable = false, length = 200)
	private String savedName;

	@Column(name = "STACK_FILE_SAVED_PATH", nullable = false, length = 200)
	private String savedPath;

	@Column(name = " STACK_FILE_TYPE", nullable = false, length = 20)
	private String type;
	
	@Column(name = " STACK_FILE_SIZE", nullable = false, length = 20)
	private Long size;
	
	@Column(name = "STACK_FILE_CREATED_BY", nullable = false, length = 60)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STACK_FILE_CREATED_DATE")
	private Date createdDate;

	@Column(name = "STACK_FILE_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STACK_FILE_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "STACK_FILE_DEL_FLAG", length=1)
	private String delFlag="N";
	
	@Transient
	private byte[] fileByte;
	
	@Transient
	private MultipartFile[] files;
}
