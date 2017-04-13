package com.shun.blog.model.file;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.shun.blog.model.blog.Blog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_FILE_DATA")
public class FileData implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID")
	private Long fileDataId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "FILE_BLOG_FK"), name = "FILE_BLOG_ID", referencedColumnName = "BLOG_ID", nullable=false)
	private Blog blogInFile;

	@Column(name = "FILE_ORIGIN_NAME", nullable = false, length = 100)
	private String fileDataOriginName;

	@Column(name = "FILE_SAVED_NAME", nullable = false, length = 200)
	private String fileDataSavedName;

	@Column(name = "FILE_SAVED_PATH", nullable = false, length = 200)
	private String fileDataSavedPath;

	@Column(name = " FILE_TYPE", nullable = false, length = 20)
	private String fileDataType;
	
	@Column(name = " FILE_SIZE", nullable = false, length = 20)
	private Long fileDataSize;
	
	@Column(name = "FILE_CREATED_BY", nullable = false, length = 60)
	private String fileDataCreatedBy;

	@Column(name = "FILE_MODIFIED_BY", length = 60)
	private String fileDataModifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_CREATED_DATE")
	private Date fileDataCreatedDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FILE_MODIFIED_DATE")
	private Date fileDataModifiedDate;

	@Column(name = "FILE_DELCHECK", length=5, nullable=false)
	private int fileDataDelCheck;
	
	@Transient
	private byte[] fileByte;
	
	@Transient
	private MultipartFile[] files;
}
