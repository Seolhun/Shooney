package com.shun.blog.model.stack;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "TB_STACK_IMG")
@BatchSize(size = 5)
public class StackFile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IMG_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "STACK_IMG_FK"), name = "STACK_IMG_ID", referencedColumnName = "STACK_ID")
	private Stack stackInFile;

	@Column(name = "STACK_IMG_ORIGIN_NAME", length = 100)
	private String originName;

	@Column(name = "STACK_IMG_SAVED_NAME", length = 200)
	private String savedName;

	@Column(name = "STACK_IMG_SAVED_PATH", length = 200)
	private String savedPath;

	@Column(name = " STACK_IMG_TYPE", length = 20)
	private String type;
	
	@Column(name = " STACK_IMG_SIZE", length = 20)
	private Long size;
	
	@Column(name = "STACK_IMG_CREATED_BY", length = 60)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STACK_IMG_CREATED_DATE")
	private Date createdDate;

	@Column(name = "STACK_IMG_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STACK_IMG_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "STACK_IMG_DEL_FLAG", length=1)
	private String delFlag="N";
}
