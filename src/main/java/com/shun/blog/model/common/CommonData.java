package com.shun.blog.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Embeddable
@Data
public class CommonData implements Serializable{
	@Column(name = "COMMON_ID")
	private Long commonId;
	
	@Column(name = "CREATED_BY", nullable = false, length = 60)
	private String createdBy;

	@Column(name = "MODIFIED_BY", length = 60)
	private String modifiedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "DELCHECK", length=5, nullable=false)
	private Integer delCheck;
}
