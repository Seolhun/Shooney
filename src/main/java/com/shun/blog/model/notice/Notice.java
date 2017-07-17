package com.shun.blog.model.notice;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "TB_NOTICE")
public class Notice implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTICE_ID")
	private Long id;

	@Column(name = "NOTICE_URI", length=100, nullable = false)
	private String uri;

	@Column(name = "NOTICE_CONTENT", length=300, nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_START_DATE")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_END_DATE")
	private Date endDate;

	@Column(name = "NOTICE_CREATED_BY", length = 60, nullable = false)
	private String createdBy;

	@Column(name = "NOTICE_MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "NOTICE_DEL_FLAG", length = 1)
	private String delFlag="N";

	public Notice(){

	}

	public Notice(Long id){
		this.id=id;
	}
}
