package com.shun.blog.model.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="TB_ACCESS_LOG")
public class AccessLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCESS_LOG_ID")
	private Long id;
	
	@Column(name = "ACCESS_LOG_IP", nullable = false, length=30)
	private String ip;

	@Column(name = "ACCESS_LOG_URL", nullable = false, length=80)
	private String url;
	
	@Column(name = "ACCESS_LOG_TIME_ZONE", length=80)
	private String timeZone;
	
	@Column(name = "ACCESS_LOG_CREATED_BY", length = 60)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACCESS_LOG_CREATED_DATE")
	private Date createdDate;
	
	@Transient
	private int type;
}
